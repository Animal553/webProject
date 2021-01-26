package com.qianfeng.entity;

public class PeopleIncrease {
    private String name;
    private Integer y;
    private String drillDown;

    public PeopleIncrease() {
    }

    public PeopleIncrease(String name, Integer y, String drillDown) {
        this.name = name;
        this.y = y;
        this.drillDown = drillDown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDrillDown() {
        return drillDown;
    }

    public void setDrillDown(String drillDown) {
        this.drillDown = drillDown;
    }

    @Override
    public String toString() {
        return "PeopleIncrease{" +
                "name='" + name + '\'' +
                ", y=" + y +
                ", drillDown='" + drillDown + '\'' +
                '}';
    }
}
