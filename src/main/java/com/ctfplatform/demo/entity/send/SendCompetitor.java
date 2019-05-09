package com.ctfplatform.demo.entity.send;

/**
 * 用于发送到比赛端的选手实体类
 */
public class SendCompetitor {
    private int id;
    private String name;
    private String account;
    private String password;
    private boolean banned;
    private String competitionId;
    private String teamAccount;
    private String realName;
    private String stuNumber;
    private String stuClass;
    private String stuCollege;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitonId) {
        this.competitionId = competitonId;
    }

    public String getTeamAccount() {
        return teamAccount;
    }

    public void setTeamAccount(String teamAccount) {
        this.teamAccount = teamAccount;
    }
}
