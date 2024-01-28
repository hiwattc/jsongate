package com.staroot.collector;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @PrePersist
    protected void onCreate() {
        updateModDt();
    }

    @PreUpdate
    protected void onUpdate() {
        updateModDt();
    }

    private void updateModDt() {
        // 현재 일시를 YYYY-MM-DD HH:MI:SS 형식으로 포맷
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // moddt 필드에 할당
        moddt = formattedDateTime;
    }
}