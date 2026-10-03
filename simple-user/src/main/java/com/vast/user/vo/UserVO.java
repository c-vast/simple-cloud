package com.vast.user.vo;

import com.vast.common.annotation.valid.InsertValid;
import com.vast.common.annotation.valid.UpdateValid;
import com.vast.common.base.vo.BaseVO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO extends BaseVO<Long> {
    @NotBlank(message = "用户名不能为空",groups = {UpdateValid.class, InsertValid.class})
    private String username;
    @NotBlank(message = "密码不能为空",groups = {UpdateValid.class, InsertValid.class})
    private String password;
    @NotBlank(message = "昵称不能为空",groups = {UpdateValid.class, InsertValid.class})
    private String nickname;
    @NotBlank(message = "邮箱不能为空",groups = {UpdateValid.class, InsertValid.class})
    private String email;
    @NotBlank(message = "手机不能为空",groups = {UpdateValid.class, InsertValid.class})
    private String mobile;
}
