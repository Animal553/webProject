package com.qianfeng.service;

import java.util.List;

public interface HomePageInformationService {
    double selectIncome();
    double selectMothIncome();
    int selectUserIncrease();
    int selectNowUserIncrease();
    List<Integer> selectRegister();
    List<Double> selectCapital();
}
