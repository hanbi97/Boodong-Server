package com.real_estate.demo.domain.enums;

import lombok.Getter;

//기능상 없어도 되지만 권한관리 하고 싶어서 넣음
@Getter
public enum  Roles {
    NOT_PERMITTED,USER,ADMIN
}
