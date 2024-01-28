package com.staroot.collector;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WasInstance {
@Id
private String id;
private String hostname;
private String ip;
private String os;
private String was;
private String lang;
private String domain;
private String uri;
private String uptime;
private String lastlog;
private String moddt;
}