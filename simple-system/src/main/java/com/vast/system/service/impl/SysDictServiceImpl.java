package com.vast.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vast.common.redis.component.RedisOperator;
import com.vast.system.entity.SysDictDO;
import com.vast.system.mapper.SysDictMapper;
import com.vast.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private RedisOperator redisOperator;

    private static final String DICT_KEY_PREFIX = "sys:dict:";

    @Override
    public String translate(String dictType, String code) {
        if (code == null) {
            return null;
        }
        Map<String, String> dictMap = getDictMap(dictType);
        return dictMap.getOrDefault(code, code);
    }

    @Override
    public void saveOrUpdate(SysDictDO dict) {
        if (dict.getId() == null) {
            sysDictMapper.insert(dict);
        } else {
            sysDictMapper.updateById(dict);
        }
        // 失效对应字典类型缓存
        refreshDict(dict.getDictType());
    }

    @Override
    public void removeById(Long id) {
        SysDictDO dict = sysDictMapper.selectById(id);
        sysDictMapper.deleteById(id);
        if (dict != null) {
            refreshDict(dict.getDictType());
        }
    }

    /**
     * 获取某个字典类型下的 code -> name 映射
     */
    private Map<String, String> getDictMap(String dictType) {
        String key = DICT_KEY_PREFIX + dictType;

        // 1. 先查 Redis Hash
        Map<Object, Object> entries = redisOperator.hashGetAll(key);
        if (entries != null && !entries.isEmpty()) {
            Map<String, String> result = new LinkedHashMap<>();
            entries.forEach((k, v) -> result.put(String.valueOf(k), String.valueOf(v)));
            return result;
        }

        // 2. 缓存未命中，查数据库
        List<SysDictDO> list = sysDictMapper.selectList(new QueryWrapper<SysDictDO>().eq("dict_type", dictType));
        Map<Object, Object> dictMap = list.stream()
                .collect(Collectors.toMap(
                        SysDictDO::getDictCode,
                        SysDictDO::getDictName,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        Map<String, String> result = new LinkedHashMap<>();
        // 3. 写入 Redis Hash
        if (!dictMap.isEmpty()) {
            redisOperator.hashSetAll(key, dictMap);

            dictMap.forEach((k, v) -> result.put(String.valueOf(k), String.valueOf(v)));
        }

        return result;
    }

    private void refreshDict(String dictType) {
        String key = DICT_KEY_PREFIX + dictType;
        redisOperator.del(key);
        // 重新加载
        getDictMap(dictType);
    }


}
