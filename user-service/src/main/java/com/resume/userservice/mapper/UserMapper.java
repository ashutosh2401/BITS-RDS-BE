package com.resume.userservice.mapper;

import org.modelmapper.PropertyMap;
import com.resume.userservice.dto.RegisterRequest;
import com.resume.userservice.entity.User;

public class UserMapper extends PropertyMap<RegisterRequest, User> {
    @Override
    protected void configure() {
        skip(destination.getId()); // Ignores mapping password field
    }
}