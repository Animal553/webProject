package com.qianfeng.entity;

/**
 * 客户级别
 */
public class Level {

    private Integer levelId;
    private String levelName;

    public Level() {
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
