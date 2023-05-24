package com.grow.common_core.auth.model;

import com.grow.common_core.domain.value_objects.UserSub;
import com.grow.common_core.domain.value_objects.Username;

public record InvitationResult(UserSub sub, Username username) {

}
