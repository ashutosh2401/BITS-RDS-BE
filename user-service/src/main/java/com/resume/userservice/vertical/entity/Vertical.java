package com.resume.userservice.vertical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resume.userservice.user.entity.User;
import com.resume.userservice.organization.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Document(collection = "verticals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vertical {
    @Id
    private String id;
    private String verticalId;
    private String name;

    @DBRef
    @JsonIgnore
    private Organization organization;

    @DBRef
    @JsonIgnore
    private List<User> users;
}