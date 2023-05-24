@TypeDefs(value = {
        @TypeDef(defaultForType = EmailAddress.class, typeClass = EmailAddressBasicType.class),
        @TypeDef(defaultForType = PhoneNumber.class, typeClass = PhoneNumberBasicType.class),
        @TypeDef(defaultForType = FaxNumber.class, typeClass = FaxNumberBasicType.class),
        @TypeDef(defaultForType = Photo.class, typeClass = PhotoBasicType.class),
        @TypeDef(defaultForType = BirthDate.class, typeClass = BirthDateBasicType.class),
        @TypeDef(defaultForType = UserId.class, typeClass = UserIdType.class),
        @TypeDef(defaultForType = WorkshopId.class, typeClass = WorkshopIdType.class),
        @TypeDef(defaultForType = PhotoGallery.class, typeClass = PhotoGalleryType.class),
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
        @TypeDef(defaultForType = Name.class, typeClass = NameBasicType.class),
        @TypeDef(defaultForType = Username.class, typeClass = UsernameBasicType.class),
        @TypeDef(defaultForType = UserSub.class, typeClass = UserSubBasicType.class),
        @TypeDef(defaultForType = Text.class, typeClass = TextBasicType.class),
       })
package com.grow.common_infra.descriptor;





import com.grow.common_core.domain.ids.UserId;
import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.common_core.domain.value_objects.BirthDate;
import com.grow.common_core.domain.value_objects.EmailAddress;
import com.grow.common_core.domain.value_objects.FaxNumber;
import com.grow.common_core.domain.value_objects.Name;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import com.grow.common_core.domain.value_objects.Photo;
import com.grow.common_core.domain.value_objects.PhotoGallery;
import com.grow.common_core.domain.value_objects.Text;
import com.grow.common_core.domain.value_objects.UserSub;
import com.grow.common_core.domain.value_objects.Username;
import com.grow.common_infra.descriptor.id.UserIdType;
import com.grow.common_infra.descriptor.id.WorkshopIdType;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
