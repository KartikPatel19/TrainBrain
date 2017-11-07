package com.deucat.kartik.trainbrain.MainWork;

public class Data {

    private String number;
    private String name;
    private String traverTime;
    private String SrcDepartTime;
    private String DestArrivTime;
    private String FromStarion;
    private String toStation;

    public Data() {
    }

    public Data(String number, String name, String traverTime, String srcDepartTime, String destArrivTime, String fromStarion, String toStation) {
        this.number = number;
        this.name = name;
        this.traverTime = traverTime;
        SrcDepartTime = srcDepartTime;
        DestArrivTime = destArrivTime;
        FromStarion = fromStarion;
        this.toStation = toStation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraverTime() {
        return traverTime;
    }

    public void setTraverTime(String traverTime) {
        this.traverTime = traverTime;
    }

    public String getSrcDepartTime() {
        return SrcDepartTime;
    }

    public void setSrcDepartTime(String srcDepartTime) {
        SrcDepartTime = srcDepartTime;
    }

    public String getDestArrivTime() {
        return DestArrivTime;
    }

    public void setDestArrivTime(String destArrivTime) {
        DestArrivTime = destArrivTime;
    }

    public String getFromStarion() {
        return FromStarion;
    }

    public void setFromStarion(String fromStarion) {
        FromStarion = fromStarion;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }
}
