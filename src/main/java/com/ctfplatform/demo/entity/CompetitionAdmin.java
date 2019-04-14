package com.ctfplatform.demo.entity;

public class CompetitionAdmin {
    private int competitionadminId;
    private int competitionId;
    private int adminId;

    public int getCompetitionadminId() {
        return competitionadminId;
    }

    public void setCompetitionadminId(int competitionadminId) {
        this.competitionadminId = competitionadminId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "CompetitionAdmin{" +
                "competitionadminId=" + competitionadminId +
                ", competitionId=" + competitionId +
                ", adminId=" + adminId +
                '}';
    }
}
