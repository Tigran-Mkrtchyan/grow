package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.Text;
import com.grow.common_infra.basictype.StringWrapperBasicType;

public class TextBasicType extends StringWrapperBasicType<Text> {

    public TextBasicType() {
        super(Text.class, Text::new, Text::getValue, false);
    }

}
