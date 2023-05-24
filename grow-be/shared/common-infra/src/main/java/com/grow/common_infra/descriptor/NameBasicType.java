package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.Name;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class NameBasicType extends StringWrapperBasicType<Name> {

    public NameBasicType() {
        super(Name.class, Name::new, Name::getValue, false);
    }

}
