package com.resume.authservice.config;

import com.resume.authservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userServiceClient", url = "${user.service.url}")
public interface UserServiceClient {
    @GetMapping("/users/email/{email}")
    UserDto getUserByEmail(@PathVariable String email);
}