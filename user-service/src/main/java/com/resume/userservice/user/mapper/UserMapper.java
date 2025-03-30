package com.resume.userservice.user.mapper;

import org.modelmapper.PropertyMap;
import com.resume.userservice.user.dto.RegisterRequest;
import com.resume.userservice.user.entity.User;

public class UserMapper extends PropertyMap<RegisterRequest, User> {
    @Override
    protected void configure() {
        skip(destination.getId()); // Ignores mapping password field
    }
}