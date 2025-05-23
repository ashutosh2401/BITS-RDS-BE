package com.resume.userservice.user.entity;

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
    private String role; // "USER" or "ADMIN"
    @DBRef
    private Organization organization; // Reference to Organization

    @DBRef
    private Vertical vertical; // Reference to Vertical
}
