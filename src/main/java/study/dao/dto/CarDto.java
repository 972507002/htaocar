package study.dao.dto;

public class CarDto {
    /**品牌根据枚举转换成int值保持*/
    private String p_brand;
    /**车系*/
    private String p_subbrand;
    /**车型，根据枚举转换成int值*/
    private String p_model;
    /**售价*/
    private String p_price;
    /**颜色*/
    private String p_color;
    /**排放标准*/
    private String p_emission;
    /**排量[升/L]*/
    private double p_gas;
    /**变速箱（手动、自动、手自动一体） 将根据枚举转换成int值*/
    private String p_transmission;
    /**上牌年*/
    private int p_year;
    /**上牌月*/
    private int p_month;
    /**行驶里程(公里/KM)*/
    private double p_kilometre;
    /**国产进口*/
    private String p_country;
    /**车主介绍,sql 中选择性varcher或者text*/
    private String p_details;
    /**车主称谓*/
    private String p_username;


    public String getP_brand() {
        return p_brand;
    }

    public void setP_brand(String p_brand) {
        this.p_brand = p_brand;
    }

    public String getP_subbrand() {
        return p_subbrand;
    }

    public void setP_subbrand(String p_subbrand) {
        this.p_subbrand = p_subbrand;
    }

    public String getP_model() {
        return p_model;
    }

    public void setP_model(String p_model) {
        this.p_model = p_model;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_color() {
        return p_color;
    }

    public void setP_color(String p_color) {
        this.p_color = p_color;
    }

    public String getP_emission() {
        return p_emission;
    }

    public void setP_emission(String p_emission) {
        this.p_emission = p_emission;
    }

    public double getP_gas() {
        return p_gas;
    }

    public void setP_gas(double p_gas) {
        this.p_gas = p_gas;
    }

    public String getP_transmission() {
        return p_transmission;
    }

    public void setP_transmission(String p_transmission) {
        this.p_transmission = p_transmission;
    }

    public int getP_year() {
        return p_year;
    }

    public void setP_year(int p_year) {
        this.p_year = p_year;
    }

    public int getP_month() {
        return p_month;
    }

    public void setP_month(int p_month) {
        this.p_month = p_month;
    }

    public double getP_kilometre() {
        return p_kilometre;
    }

    public void setP_kilometre(double p_kilometre) {
        this.p_kilometre = p_kilometre;
    }

    public String getP_country() {
        return p_country;
    }

    public void setP_country(String p_country) {
        this.p_country = p_country;
    }

    public String getP_details() {
        return p_details;
    }

    public void setP_details(String p_details) {
        this.p_details = p_details;
    }

    public String getP_username() {
        return p_username;
    }

    public void setP_username(String p_username) {
        this.p_username = p_username;
    }

}
