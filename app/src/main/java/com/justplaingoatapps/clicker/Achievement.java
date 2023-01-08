package com.justplaingoatapps.clicker;

public class Achievement {

    private String achievementName;
    private String achievementDescription;
    private int achievementPic;
    private boolean visible;
    private int checkPic;
    private boolean croutonDisplayed;

    public Achievement(String achievementName, int achievementPic, String achievementDescription, int checkPic, boolean visible, boolean croutonDisplayed) {
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.achievementPic = achievementPic;
        this.checkPic = checkPic;
        this.visible = visible;
        this.croutonDisplayed = croutonDisplayed;
    }

    public int getCheckPic() {
        return checkPic;
    }

    public int getAchievementPic() {
        return achievementPic;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public boolean isCroutonDisplayed() {
        return croutonDisplayed;
    }

    public void setCroutonDisplayed(boolean croutonDisplayed) {
        this.croutonDisplayed = croutonDisplayed;
    }

}
