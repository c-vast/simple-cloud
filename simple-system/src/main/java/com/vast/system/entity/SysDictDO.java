package com.vast.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vast.common.base.entity.BaseDO;
import lombok.Data;

@Data
@TableName("t_sys_dict")
public class SysDictDO extends BaseDO<Long, SysDictDO> {
    private String dictType;
    private String dictCode;
    private String dictName;
    private String dictValue;
    private Integer sortNo;
    private Integer status;
    private String remark;
}
