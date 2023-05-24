package com.grow.common_infra.auth;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.DeliveryMediumType;
import com.amazonaws.services.cognitoidp.model.MessageActionType;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.grow.common_core.auth.AuthClient;
import com.grow.common_core.auth.model.ClientMetadataKey;
import com.grow.common_core.auth.model.CustomAttribute;
import com.grow.common_core.auth.model.InvitationResult;
import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_core.domain.value_objects.Password;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import com.grow.common_core.domain.value_objects.UserSub;
import com.grow.common_core.domain.value_objects.Username;
import com.grow.common_infra.auth.model.CognitoClaim;
import com.grow.common_infra.config.AWSConfig;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AuthClientCognitoImpl implements AuthClient {

    private final AWSCognitoIdentityProvider cognitoIDP;
    private final AWSConfig awsConfig;

    public AuthClientCognitoImpl(AWSCognitoIdentityProvider cognitoIDP,
                                 AWSConfig awsConfig) {
        this.cognitoIDP = cognitoIDP;
        this.awsConfig = awsConfig;
    }

    @Override
    public InvitationResult createUser(@NonNull Username username,
                                       EmailAddress email,
                                       PhoneNumber phone,
                                       Map<ClientMetadataKey, String> clientMetadata) {

        return createUser(username, email, phone, clientMetadata, new ArrayList<>());
    }

    @Override
    public InvitationResult createUser(@NonNull Username username, EmailAddress email, PhoneNumber phone,
                                       Map<CustomAttribute, String> customAttributes,
                                       Map<ClientMetadataKey, String> clientMetadata) {

        List<AttributeType> cognitoAttrs = toCognitoAttributes(customAttributes);

        return createUser(username, email, phone, clientMetadata, cognitoAttrs);
    }

    @NonNull
    private Map<String, String> toCognitoClientMetadata(Map<ClientMetadataKey, String> clientMetadata) {
        Map<String, String> ret = new HashMap<>();
        if (clientMetadata == null) return ret;
        clientMetadata.forEach((k, v) -> {
            if (v != null) ret.put(k.key, v);
        });
        return ret;
    }

    @Override
    public InvitationResult signUpUser(@NonNull Username username, EmailAddress email, PhoneNumber phone,
                                       Password password, Map<CustomAttribute, String> attrs) {
        SignUpRequest request = new SignUpRequest();
        List<AttributeType> cognitoAttrs = new ArrayList<>();
        addEmailPhoneAttr(email, phone, cognitoAttrs);
        cognitoAttrs.addAll(toCognitoAttributes(attrs));
        request.withUserAttributes(cognitoAttrs)
                .withClientId(awsConfig.getCognitoWebClientId())
                .withClientMetadata(toCognitoClientMetadata(null))
                .withUsername(username.value())
                .withPassword(password.getPassword());

        SignUpResult signUpResult = cognitoIDP.signUp(request);

        return new InvitationResult(new UserSub(signUpResult.getUserSub()), username);
    }

    @NonNull
    private AttributeType toCognitoAttribute(@NonNull CustomAttribute key,
                                             @NonNull String value) {
        return new AttributeType().withName(key.key).withValue(value);
    }

    @NonNull
    private List<AttributeType> toCognitoAttributes(Map<CustomAttribute, String> attrs) {
        if (CollectionUtils.isEmpty(attrs)) {
            return Collections.emptyList();
        }
        return attrs.entrySet().stream().filter(a -> a.getValue() != null)
                .map(a -> toCognitoAttribute(a.getKey(), a.getValue()))
                .collect(Collectors.toList());
    }


    private InvitationResult createUser(@NonNull Username username, EmailAddress email, PhoneNumber phone,
                                        Map<ClientMetadataKey, String> clientMetadata,
                                        @NonNull List<AttributeType> cognitoAttrs) {
        AdminCreateUserRequest request = new AdminCreateUserRequest();
        DeliveryMediumType deliveryMediumType = determineDeliveryType(username, email);
        addEmailPhoneAttr(email, phone, cognitoAttrs);
        setDeliveryMediumVerified(cognitoAttrs, deliveryMediumType);
        String temporaryPassword = getOrCreateTempPassword(username.value());
        addAttribute(cognitoAttrs, temporaryPassword, CognitoClaim.TEMPORARY_PASSWORD);
        request.withUserAttributes(cognitoAttrs)
                .withMessageAction(MessageActionType.RESEND)
                .withUsername(username.value())
                .withTemporaryPassword(temporaryPassword)
                .withUserPoolId(awsConfig.getCognitoUserPoolId())
                .withClientMetadata(toCognitoClientMetadata(clientMetadata))
                .withDesiredDeliveryMediums(deliveryMediumType);

        AdminCreateUserResult result;
        try {
            result = cognitoIDP.adminCreateUser(request);
        } catch (UserNotFoundException e) {
            request.withMessageAction((String) null);
            result = cognitoIDP.adminCreateUser(request);
        }

        return new InvitationResult(new UserSub(result.getUser().getUsername()), username);
    }

    private DeliveryMediumType determineDeliveryType(@NonNull Username username, EmailAddress email) {
        return email != null && username.value().equalsIgnoreCase(email.getValue())
                ? DeliveryMediumType.EMAIL : DeliveryMediumType.SMS;
    }

    private void addEmailPhoneAttr(EmailAddress email, PhoneNumber phone, List<AttributeType> cognitoAttrs) {
        if (email != null) {
            cognitoAttrs.add(toCognitoAttribute(CustomAttribute.EMAIL, email.getValue()));
        }
        if (phone != null) {
            cognitoAttrs.add(toCognitoAttribute(CustomAttribute.PHONE, phone.getValue()));
        }
    }

    private String generateTempPassword() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(uuid.length() - 7);
    }

    private void setDeliveryMediumVerified(List<AttributeType> cognitoAttrs,
                                           DeliveryMediumType deliveryMediumType) {
        if (deliveryMediumType == DeliveryMediumType.EMAIL) {
            cognitoAttrs.add(new AttributeType().withName("email_verified").withValue("true"));
        } else {
            cognitoAttrs.add(new AttributeType().withName("phone_number_verified").withValue("true"));
        }
    }

    private void addAttribute(List<AttributeType> attrs, String valueGetter,
                              CognitoClaim cognitoClaim) {
        attrs.add(
                new AttributeType().withValue(valueGetter)
                        .withName(cognitoClaim.key())
        );
    }

    private Optional<String> findAttribute(List<AttributeType> attrs, CognitoClaim claim) {
        return attrs.stream()
                .filter(attr -> attr.getName().equals(claim.key()))
                .findFirst()
                .map(AttributeType::getValue);
    }

    private Optional<AdminGetUserResult> findUser(String username) {
        AdminGetUserRequest request = new AdminGetUserRequest()
                .withUsername(username)
                .withUserPoolId(awsConfig.getCognitoUserPoolId());
        try {
            return Optional.of(cognitoIDP.adminGetUser(request));
        } catch (UserNotFoundException e) {
            return Optional.empty();
        }
    }

    private String getOrCreateTempPassword(String username) {
        Optional<AdminGetUserResult> userOpt = this.findUser(username);
        if (userOpt.isEmpty()) {
            return generateTempPassword();
        }
        return findAttribute(userOpt.get().getUserAttributes(), CognitoClaim.TEMPORARY_PASSWORD)
                .orElseGet(this::generateTempPassword);
    }

}
