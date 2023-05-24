package com.grow.common_core.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.grow.common_core.exception.PhoneNumberParsingException;

public class PhoneNumberUtils {

    public static String normalize(String phoneNumberStr) {
        try {
            PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneProto = instance.parse(phoneNumberStr, "US");
            return instance.format(phoneProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            throw new PhoneNumberParsingException(String.format("Can't normalize '%s' phone number", phoneNumberStr));
        }
    }

}
