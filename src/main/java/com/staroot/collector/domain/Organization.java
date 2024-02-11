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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Id
    private String orgCd;
    private String orgNm;
    private String upOrgCd;
    private String sq;
    private String mgrId;
    private String mgrNm;

    // getters and setters
    @Getter
    @Transient
    private List<Organization> children = new ArrayList<>();

    public void addChild(Organization child) {
        children.add(child);
    }
}
