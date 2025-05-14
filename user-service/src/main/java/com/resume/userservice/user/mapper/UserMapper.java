package com.resume.userservice.user.mapper;

import org.modelmapper.PropertyMap;
import com.resume.userservice.auth.request.RegisterRequest;
import com.resume.userservice.user.entity.User;

public class UserMapper extends PropertyMap<RegisterRequest, User> {
    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getOrganization());
        skip(destination.getVertical());
        skip(destination.getRole());
    }
}