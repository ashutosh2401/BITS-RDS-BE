package com.resume.userservice.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resume.userservice.organization.entity.Organization;
import com.resume.userservice.vertical.entity.Vertical;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;

    @DBRef
    @JsonIgnore
    private Organization organization;

    @DBRef
    @JsonIgnore
    private Vertical vertical;
}
