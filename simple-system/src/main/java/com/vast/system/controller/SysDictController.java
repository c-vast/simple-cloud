package com.vast.system.controller;

import com.vast.common.web.result.Result;
import com.vast.common.web.result.ResultWapper;
import com.vast.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sysDict")
@RestController
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @PostMapping("/translate")
    public Result<String> translate(@RequestParam String dictType,@RequestParam String code) {
        return ResultWapper.success("翻译成功",sysDictService.translate(dictType, code));
    }
}
