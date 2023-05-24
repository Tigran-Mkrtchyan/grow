package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.PhoneNumber;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class PhoneNumberBasicType extends StringWrapperBasicType<PhoneNumber> {

    public PhoneNumberBasicType() {
        super(PhoneNumber.class, PhoneNumber::new, PhoneNumber::getValue, false);
    }

}
