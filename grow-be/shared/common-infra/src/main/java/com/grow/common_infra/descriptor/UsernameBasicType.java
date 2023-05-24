package com.grow.common_infra.descriptor;


import com.grow.common_core.domain.value_objects.Username;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class UsernameBasicType extends StringWrapperBasicType<Username> {

    public UsernameBasicType() {
        super(Username.class, Username::new, Username::value, false);
    }

}
