package com.vast.system.service;

import com.vast.system.entity.SysDictDO;

public interface SysDictService {
    String translate(String dictType, String code);
    void saveOrUpdate(SysDictDO dict);
    void removeById(Long id);
}
