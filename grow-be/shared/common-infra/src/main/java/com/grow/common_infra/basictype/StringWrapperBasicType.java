package com.grow.common_infra.basictype;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.function.Function;

public class StringWrapperBasicType<T> extends AbstractSingleColumnStandardBasicType<T> {

    public StringWrapperBasicType(Class<T> tClass,
                                  Function<String, T> creator,
                                  Function<T, String> extractor,
                                  boolean createWithEmptyValue) {
        super(VarcharTypeDescriptor.INSTANCE, new StringWrapperTypeDescripto<T>(tClass, creator, extractor, createWithEmptyValue));
    }

    @Override
    public String getName() {
        return null;
    }

    private static class StringWrapperTypeDescripto<T> extends AbstractTypeDescriptor<T> {

        private Function<String, T> creator;
        private Function<T, String> extractor;
        private boolean createWithEmptyValue;

        protected <B> StringWrapperTypeDescripto(Class<T> tClass,
                                                 Function<String, T> creator,
                                                 Function<T, String> extractor,
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
            return creator.apply(s);
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
                    return creator.apply((String) x);
                }
                return null;
            }
            return creator.apply((String) x);
        }

    }

}
