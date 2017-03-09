package com.deucat.kartik.trainbrain.Route;

public class TrainClass {

    private String mNameOfTrain = "";
    private String[] mClassName;
    private String[] mDayName;


    public TrainClass() {    }


    public String getNameOfTrain() {
        return mNameOfTrain;
    }

    public void setNameOfTrain(String nameOfTrain) {
        mNameOfTrain = nameOfTrain;
    }

    public String[] getClassName() {
        return mClassName;
    }

    public void setClassName(String[] className) {
        mClassName = className;
    }

    public String[] getDayName() {
        return mDayName;
    }

    public void setDayName(String[] dayName) {
        mDayName = dayName;
    }

}
