package com.ay.exchange.user.dto.query;

import com.ay.exchange.user.entity.Authority;

public interface UserInfoDto {
    String getPassword();
    String getNickName();
    Authority getAuthority();
    String getSuspendedDate();
}
