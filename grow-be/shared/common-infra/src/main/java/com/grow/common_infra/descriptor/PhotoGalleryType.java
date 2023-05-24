package com.grow.common_infra.descriptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.common_core.domain.value_objects.PhotoGallery;
import com.vladmihalcea.hibernate.type.json.internal.JsonBinarySqlTypeDescriptor;
import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutabilityPlan;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.util.HashSet;

public class PhotoGalleryType extends AbstractSingleColumnStandardBasicType<PhotoGallery> {

    public PhotoGalleryType() {
        super(JsonBinarySqlTypeDescriptor.INSTANCE, new PhotoGalleryDescriptor());
    }

    @Override
    public String getName() {
        return getJavaTypeDescriptor().getJavaType().getSimpleName();
    }

    @Override
    public Object resolve(Object value, SharedSessionContractImplementor session, Object owner, Boolean overridingEager) throws HibernateException {
        Object resolve = super.resolve(value, session, owner, overridingEager);
        if (resolve == null) {
            return new PhotoGallery();
        }
        return resolve;
    }

    private static class PhotoGalleryDescriptor extends AbstractTypeDescriptor<PhotoGallery> {

        private ObjectMapper objectMapper = new ObjectMapper();

        protected PhotoGalleryDescriptor() {
            super(PhotoGallery.class);
        }

        @Override
        public boolean areEqual(PhotoGallery one, PhotoGallery another) {
            return one.equals(another);
        }

        @Override
        public MutabilityPlan<PhotoGallery> getMutabilityPlan() {
            return new MutabilityPlan<PhotoGallery>() {
                @Override
                public boolean isMutable() {
                    return true;
                }

                @Override
                public PhotoGallery deepCopy(PhotoGallery photoGallery) {
                    if (photoGallery == null) {
                        return new PhotoGallery();
                    }
                    return new PhotoGallery(new HashSet<>(photoGallery.getValue()));
                }

                @Override
                public Serializable disassemble(PhotoGallery photoGallery) {
                    return null;
                }

                @Override
                public PhotoGallery assemble(Serializable serializable) {
                    return null;
                }
            };
        }

        @Override
        public PhotoGallery fromString(String s) {
            return null;
        }

        @Override
        public <X> X unwrap(PhotoGallery photoGallery, Class<X> aClass, WrapperOptions wrapperOptions) {
            try {
                if (photoGallery == null) {
                    return null;
                }
                JsonNode jsonNode1 = objectMapper.convertValue(photoGallery, JsonNode.class);
                return aClass.cast(jsonNode1);
            } catch (Exception e) {
                throw unknownUnwrap(aClass);
            }
        }

        @SneakyThrows
        @Override
        public <X> PhotoGallery wrap(X x, WrapperOptions wrapperOptions) {
            if (x == null) {
                return new PhotoGallery();
            }
            PGobject x1 = (PGobject) x;
            String value = x1.getValue();
            try {
                return objectMapper.readValue(value, PhotoGallery.class);
            } catch (JsonProcessingException e) {
                throw unknownWrap(PhotoGallery.class);
            }
        }

    }

}
