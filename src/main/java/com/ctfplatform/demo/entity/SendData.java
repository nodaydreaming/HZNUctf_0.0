package com.ctfplatform.demo.entity;

public class SendData {
    private Integer datasendId;
    private String competitionId;
    private String sendIp;
    private String sendPort;
    private String account;
    private String password;

    public Integer getDatasendId() {
        return datasendId;
    }

    public void setDatasendId(Integer datasendId) {
        this.datasendId = datasendId;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    public String getSendPort() {
        return sendPort;
    }

    public void setSendPort(String sendPort) {
        this.sendPort = sendPort;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
