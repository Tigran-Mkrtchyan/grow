package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.BirthDate;
import com.grow.common_infra.basictype.TimestampWrapperBasicType;

import java.sql.Timestamp;

public class BirthDateBasicType extends TimestampWrapperBasicType<BirthDate> {

    public BirthDateBasicType() {
        super(BirthDate.class, (timestamp -> {
            return timestamp != null ? new BirthDate(timestamp.toLocalDateTime().toLocalDate())
                    : null;
        }), (birthDate -> {
            return birthDate != null ? Timestamp.valueOf(birthDate.getValue().atStartOfDay())
                    : null;
        }));
    }

}
