package com.ctfplatform.demo.entity.send;

/**
 * 用于发送到比赛端的题目实体类
 */
public class SendProblem {
    private int id;
    private String competitionId;
    private int problemId;//题目序号
    private String problemType;
    private String author;
    private String title;
    private int level;//百分制难度，越高越难
    private String description;
    private String resourceURL;
    private int baseScore;
    private int extraScore;
    private int fullScore;
    private double scoreStep;
    private String answer;

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

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public int getExtraScore() {
        return extraScore;
    }

    public void setExtraScore(int extraScore) {
        this.extraScore = extraScore;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public double getScoreStep() {
        return scoreStep;
    }

    public void setScoreStep(double scoreStep) {
        this.scoreStep = scoreStep;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SendProblem{" +
                "id=" + id +
                ", competitionId='" + competitionId + '\'' +
                ", problemId=" + problemId +
                ", problemType='" + problemType + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", resourceURL='" + resourceURL + '\'' +
                ", baseScore=" + baseScore +
                ", extraScore=" + extraScore +
                ", fullScore=" + fullScore +
                ", scoreStep=" + scoreStep +
                ", answer='" + answer + '\'' +
                '}';
    }
}
