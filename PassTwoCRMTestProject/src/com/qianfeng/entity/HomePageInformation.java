package com.qianfeng.entity;

import java.util.List;

public class HomePageInformation {
    private double income;
    private double mothIncome;
    private int userIncrease;
    private int nowUserIncrease;
    private List<Integer> register ;
    private List<Double> capital;


    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getMothIncome() {
        return mothIncome;
    }

    public void setMothIncome(double mothIncome) {
        this.mothIncome = mothIncome;
    }

    public int getUserIncrease() {
        return userIncrease;
    }

    public void setUserIncrease(int userIncrease) {
        this.userIncrease = userIncrease;
    }

    public int getNowUserIncrease() {
        return nowUserIncrease;
    }

    public void setNowUserIncrease(int nowUserIncrease) {
        this.nowUserIncrease = nowUserIncrease;
    }

    public List<Integer> getRegister() {
        return register;
    }

    public void setRegister(List<Integer> register) {
        this.register = register;
    }

    public List<Double> getCapital() {
        return capital;
    }

    public void setCapital(List<Double> capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "HomePageInformation{" +
                "income=" + income +
                ", mothIncome=" + mothIncome +
                ", userIncrease=" + userIncrease +
                ", nowUserIncrease=" + nowUserIncrease +
                ", register=" + register +
                ", capital=" + capital +
                '}';
    }
}
