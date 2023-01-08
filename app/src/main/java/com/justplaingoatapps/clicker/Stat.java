package com.justplaingoatapps.clicker;

/**
 * Created by Luke on 9/9/2015.
 */
public class Stat {

    private String statDesc;
    private double statNum;

    public Stat(String statDesc, double statNum) {
        this.statDesc = statDesc;
        this.statNum = statNum;
    }

    public String getStatDesc() {
        return statDesc;
    }

    public void setStatDesc(String statDesc) {
        this.statDesc = statDesc;
    }

    public double getStatNum() {
        return statNum;
    }

    public void setStatNum(int statNum) {
        this.statNum = statNum;
    }

}
