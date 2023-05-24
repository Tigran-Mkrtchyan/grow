package com.grow.common_core.storage;

import com.grow.common_core.domain.value_objects.File;
import com.grow.common_core.storage.model.UploadFileRequest;
import lombok.NonNull;

public interface StorageService {

    File upload(@NonNull UploadFileRequest request);
}
