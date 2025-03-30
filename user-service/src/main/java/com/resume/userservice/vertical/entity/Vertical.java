package com.resume.userservice.vertical.entity;

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
    private String name;

    @DBRef
    private Organization organization; // Reference to Organization

    @DBRef
    private List<User> users; // References multiple users
}

