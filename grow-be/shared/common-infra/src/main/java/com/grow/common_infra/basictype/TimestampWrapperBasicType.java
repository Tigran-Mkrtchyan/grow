package com.grow.common_infra.basictype;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;

import java.sql.Timestamp;
import java.util.function.Function;

public class TimestampWrapperBasicType<T> extends AbstractSingleColumnStandardBasicType<T> {

    public TimestampWrapperBasicType(Class<T> tClass,
                                     Function<Timestamp, T> creator,
                                     Function<T, Timestamp> extractor) {
        super(org.hibernate.type.descriptor.sql.TimestampTypeDescriptor.INSTANCE, new TimestampTypeDescriptor<>(tClass, creator, extractor, false));
    }

    public TimestampWrapperBasicType(Class<T> tClass,
                                     Function<Timestamp, T> creator,
                                     Function<T, Timestamp> extractor,
                                     boolean createWithEmptyValue) {
        super(org.hibernate.type.descriptor.sql.TimestampTypeDescriptor.INSTANCE, new TimestampTypeDescriptor<>(tClass, creator, extractor, createWithEmptyValue));
    }

    @Override
    public String getName() {
        return null;
    }

    private static class TimestampTypeDescriptor<T> extends AbstractTypeDescriptor<T> {

        private Function<Timestamp, T> creator;
        private Function<T, Timestamp> extractor;
        private boolean createWithEmptyValue;

        protected TimestampTypeDescriptor(Class<T> tClass,
                                          Function<Timestamp, T> creator,
                                          Function<T, Timestamp> extractor,
                                          boolean createWithEmptyValue) {
            super(tClass);
            this.creator = creator;
            this.extractor = extractor;
            this.createWithEmptyValue = createWithEmptyValue;
        }


        @Override
        public T fromString(String s) {
            if (s == null) {
                return null;
            }
            Timestamp timestamp = Timestamp.valueOf(s);
            return creator.apply(timestamp);
        }

        @Override
        public <X> X unwrap(T t, Class<X> aClass, WrapperOptions wrapperOptions) {
            if (t == null) {
                return null;
            }
            return aClass.cast(extractor.apply(t));
        }

        @Override
        public <X> T wrap(X x, WrapperOptions wrapperOptions) {
            if (x == null) {
                if (createWithEmptyValue) {
                    return creator.apply((Timestamp) x);
                }
                return null;
            }
            return creator.apply((Timestamp) x);
        }

    }

}
