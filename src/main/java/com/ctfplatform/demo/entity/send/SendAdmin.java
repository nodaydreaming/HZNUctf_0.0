package com.ctfplatform.demo.entity.send;

import java.util.Objects;

/**
 * 用于发送到比赛端的管理员实体类
 */
public class SendAdmin {
    private int id;
    private String competitionId;
    private String name;
    private String account;
    private String password;
    private boolean banned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendAdmin)) return false;
        SendAdmin sendAdmin = (SendAdmin) o;
        return getId() == sendAdmin.getId() &&
                isBanned() == sendAdmin.isBanned() &&
                Objects.equals(getCompetitionId(), sendAdmin.getCompetitionId()) &&
                Objects.equals(getName(), sendAdmin.getName()) &&
                Objects.equals(getAccount(), sendAdmin.getAccount()) &&
                Objects.equals(getPassword(), sendAdmin.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompetitionId(), getName(), getAccount(), getPassword(), isBanned());
    }
}
