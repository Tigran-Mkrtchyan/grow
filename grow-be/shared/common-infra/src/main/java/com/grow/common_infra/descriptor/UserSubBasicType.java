package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.UserSub;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class UserSubBasicType extends StringWrapperBasicType<UserSub> {

    public UserSubBasicType() {
        super(UserSub.class, UserSub::new, UserSub::value, false);
    }

}
