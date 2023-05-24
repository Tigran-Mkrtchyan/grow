package com.grow.common_core.auth;

import com.grow.common_core.auth.model.ClientMetadataKey;
import com.grow.common_core.auth.model.CustomAttribute;
import com.grow.common_core.auth.model.InvitationResult;
import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_core.domain.value_objects.Password;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import com.grow.common_core.domain.value_objects.Username;
import lombok.NonNull;

import java.util.Map;

public interface AuthClient {

    InvitationResult createUser(@NonNull Username username,
                                EmailAddress email,
                                PhoneNumber phone,
                                Map<ClientMetadataKey, String> clientMetadata);

    InvitationResult signUpUser(@NonNull Username username,
                                EmailAddress email,
                                PhoneNumber phone,
                                @NonNull Password password,
                                Map<CustomAttribute, String> attrs);

    InvitationResult createUser(@NonNull Username username,
                                EmailAddress email,
                                PhoneNumber phone,
                                Map<CustomAttribute, String> customAttributes,
                                Map<ClientMetadataKey, String> clientMetadata);

}
