package com.example.attendance.Domin;

public class Week {
    String WeekName;
    String weekID;
    String weekDate;


    public Week() {
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public Week(String weekNumber) {
        WeekName = weekNumber;
    }

    public String getWeekName() {
        return WeekName;
    }

    public void setWeekName(String weekName) {
        WeekName = weekName;
    }
}
