package com.staroot.collector;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class ServerInfo {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String hostname;
private String ip;
private String cmd;
private String result;

protected ServerInfo() {}

public ServerInfo(String hostname,String ip, String cmd, String result) {
this.hostname = hostname;
this.ip = ip;
this.cmd = cmd;
this.result = result;
}

public String getHostname(){
    return this.hostname;
}
public String getIp(){
    return this.ip;
}
public String getCmd(){
    return this.cmd;
}
public String getResult(){
    return this.result;
}


@Override
public String toString() {
return String.format("ServerInfo[hostname='%s', ip='%s', cmd='%s' , result='%s']",hostname, ip, cmd, result);
}
}