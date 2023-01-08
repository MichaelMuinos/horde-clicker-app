package com.justplaingoatapps.clicker;


public class Upgrade {

    private String nameOfUpgrade;
    private double costOfUpgrade;
    private int upgradePicId;
    private int numberOfClicksOnUpgrade;
    private int zombiesPerSecond;
    private int clickTotal;

    public Upgrade(String nameOfUpgrade,double costOfUpgrade, int upgradePicId, int numberOfClicksOnUpgrade, int zombiesPerSecond, int clickTotal) {
        this.nameOfUpgrade = nameOfUpgrade;
        this.costOfUpgrade = costOfUpgrade;
        this.upgradePicId = upgradePicId;
        this.numberOfClicksOnUpgrade = numberOfClicksOnUpgrade;
        this.zombiesPerSecond = zombiesPerSecond;
        this.clickTotal = clickTotal;
    }

    public int getClickTotal() {
        return clickTotal;
    }

    public double calculateNewCostOfUpgrade(double upgradeCost) {
        upgradeCost = upgradeCost + (upgradeCost * 0.20);
        upgradeCost = Math.floor(upgradeCost);
        return upgradeCost;
    }

    public String getUpgradeName() {
        return nameOfUpgrade;
    }

    public int getZombiesPerSecond() {
        return zombiesPerSecond;
    }

    public double getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public int getUpgradePicId() {
        return upgradePicId;
    }

    public int getNumberOfClicksOnUpgrade() {
        return numberOfClicksOnUpgrade;
    }

    public void setNumberOfClicksOnUpgrade(int numberOfClicksOnUpgrade) {
        this.numberOfClicksOnUpgrade = numberOfClicksOnUpgrade;
    }

    public void setCostOfUpgrade(double costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

}
