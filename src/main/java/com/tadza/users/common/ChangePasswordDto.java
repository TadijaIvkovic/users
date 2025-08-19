package com.tadza.users.common;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String oldPassword;
    private String newPassword;

}
