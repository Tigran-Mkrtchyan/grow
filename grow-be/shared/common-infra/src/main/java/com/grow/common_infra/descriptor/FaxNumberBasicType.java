package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.FaxNumber;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class FaxNumberBasicType extends StringWrapperBasicType<FaxNumber> {

    public FaxNumberBasicType() {
        super(FaxNumber.class, FaxNumber::new, FaxNumber::getValue, false);
    }

}
