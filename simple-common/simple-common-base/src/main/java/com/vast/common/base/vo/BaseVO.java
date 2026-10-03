package com.vast.common.base.vo;

import com.vast.common.annotation.valid.UpdateValid;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Copyright (C), 2020-2021, c-vast
 *
 * @version 1.0.0
 * @className: BaseVo
 * @author: hechenghao1998@foxmail.com
 * @createDate: 2021/7/25 2:09
 * @description:
 */
@Data
public abstract class BaseVO<ID extends Serializable> implements Serializable {
    @NotNull(message = "编号不存在",groups = UpdateValid.class)
    private ID id;
}
