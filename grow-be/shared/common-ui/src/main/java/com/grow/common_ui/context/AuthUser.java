package com.grow.common_ui.context;

import com.grow.common_core.auth.model.CustomAttribute;
import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import com.grow.common_core.domain.value_objects.UserSub;
import com.grow.common_core.domain.value_objects.Username;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;
import java.util.Optional;

public class AuthUser {

    public static UserSub retrieveSub() {
        Jwt jwt = retrieveJwt();

        String sub = findProperty(jwt.getClaims(), CustomAttribute.SUB.key, String.class)
                .orElseThrow(() -> new RuntimeException("Sub must not be null"));
        return new UserSub(sub);
    }

    public static Optional<EmailAddress> retrieveEmail() {
        Jwt jwt = retrieveJwt();
        Object email = jwt.getClaims().get(CustomAttribute.EMAIL.key);
        if (email == null) {
            return Optional.empty();
        }
        if (!(email instanceof String)) {
            throw new RuntimeException("Email is not string");
        }
        return Optional.of(new EmailAddress((String) email));
    }

    public static Optional<PhoneNumber> retrievePhone() {
        Jwt jwt = retrieveJwt();
        Object phone = jwt.getClaims().get(CustomAttribute.PHONE.key);
        if (phone == null) {
            return Optional.empty();
        }
        if (!(phone instanceof String)) {
            throw new RuntimeException("Email is not string");
        }
        return Optional.of(new PhoneNumber((String) phone));
    }

    public static Username retrieveUsername() {
        Optional<EmailAddress> emailAddress = retrieveEmail();
        Optional<PhoneNumber> phoneNumber = retrievePhone();
        return emailAddress.map(Username::new)
                .or(() -> phoneNumber.map(Username::new))
                .orElseThrow(() -> new RuntimeException("No email or phone present to construct username"));
    }

    private static Jwt retrieveJwt() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Jwt)) {
            throw new RuntimeException("Principal is not valid jwt");
        }
        return (Jwt) principal;
    }

    private static <T> Optional<T> findProperty(Map<String, Object> claims, String key, Class<T> expectedType) {
        Object value = claims.get(key);
        if (value == null) return Optional.empty();
        T tValue;
        try {
            tValue = expectedType.cast(value);
        } catch (ClassCastException e) {
            throw new RuntimeException(String.format(
                    "Expected '%s' key to be of type '%s' but found '%s'", key, expectedType.getName(), value.getClass().getName()));
        }
        return Optional.of(tValue);
    }

}
