package com.resume.userservice.organization.entity;

import com.resume.userservice.vertical.entity.Vertical;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "organizations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {
    @Id
    private String id;
    private String orgId;
    private String name;

    @DBRef
    private List<Vertical> verticals;

}
