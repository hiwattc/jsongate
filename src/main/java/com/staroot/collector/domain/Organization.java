package com.staroot.collector.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgCd;
    private String orgNm;
    private String upOrgCd;

    // getters and setters
    @Getter
    @Transient
    private List<Organization> children = new ArrayList<>();

    public void addChild(Organization child) {
        children.add(child);
    }
}
