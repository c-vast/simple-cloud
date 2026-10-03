package com.vast.auth.feign;

import com.vast.auth.dto.UserDTO;
import com.vast.common.web.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "simple-user", path = "/")
public interface UserFeignClient {
    @GetMapping("/user/getUser")
    Result<UserDTO> getUser(@RequestParam("username") String username);
}
