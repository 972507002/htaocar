package study.dao.entity;

import java.sql.Timestamp;

public class CarsEntity {
    private String carId;
    private String phoneNumber;
    private String personId;
    /**车主称谓*/
    private String ownName;
    /**品牌根据枚举转换成int值保持*/
    private String brand;
    /**车系*/
    private String series;
    /**车型，根据枚举转换成int值*/
    private String type;
    /**车辆颜色*/
    private String color;
    /**车龄（无小数，不满年限算以内）*/
    private double age;
    /**上牌时间*/
    private String plateTime;
    /**行驶里程(公里/KM)*/
    private double mileage;
    /**变速箱（手动、自动、手自动一体） 将根据枚举转换成int值*/
    private String gearBox;
    /**排放标准*/
    private String emission;
    /**排量[升/L]*/
    private double displacement;
    /**国产进口*/
    private String country;
    /**车主介绍,sql 中选择性varcher或者text*/
    private String introduce;
    /**添加时间*/
    private Timestamp addTime;
    /**售价*/
    private double price;
    /**是否认证*/
    private int isAudit;
    /**是否删除*/
    private int isDelete;

    public CarsEntity() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }


    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(int isAudit) {
        this.isAudit = isAudit;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlateTime() {
        return plateTime;
    }

    public void setPlateTime(String plateTime) {
        this.plateTime = plateTime;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }
}
