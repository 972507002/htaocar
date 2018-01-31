package study.dao.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 * 车辆简略信息模板
 */
public class CarInfoDto {
    /**车辆id*/
    private String carId;
    /**车主电话号码*/
    private String OwnPhone;
    /**车辆主图url*/
    private String carUrl;
    /**车辆附图List*/
    private List<String> urlList;
    /**车辆品牌*/
    private String brand;
    /**车系*/
    private String series;
    /**[款式年限、是否有效]*/
    private String style;
    /**车辆上牌时间*/
    private String plateTime;
    /**发布时间*/
    private String addTime;
    /**报价*/
    private double price;
    /**车辆的收藏id*/
    private String collectId;
    /**是否被当前用户收藏*/
    private int isCollect;
    /**国产进口*/
    private String country;
    /**排量[升/L]*/
    private double displacement;

    private int isAudit;

    /**车辆颜色*/
    private String color;
    /**行驶里程*/
    private double mileage;

    /**变速箱（手动、自动、手自动一体）*/
    private String gearBox;
    /**车主介绍,*/
    private String introduce;

    public int getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(int isAudit) {
        this.isAudit = isAudit;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarUrl() {
        return carUrl;
    }

    public void setCarUrl(String carUrl) {
        this.carUrl = carUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPlateTime() {
        return plateTime;
    }

    public void setPlateTime(String plateTime) {
        this.plateTime = plateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public String getOwnPhone() {
        return OwnPhone;
    }

    public void setOwnPhone(String ownPhone) {
        OwnPhone = ownPhone;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
