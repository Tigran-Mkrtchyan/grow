package com.grow.common_infra.descriptor;


import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class EmailAddressBasicType extends StringWrapperBasicType<EmailAddress> {

    public EmailAddressBasicType() {
        super(EmailAddress.class, EmailAddress::new, EmailAddress::getValue, false);
    }

}
