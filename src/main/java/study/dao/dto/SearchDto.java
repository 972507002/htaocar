package study.dao.dto;

public class SearchDto {
    private String keyWord;
    private double lPrice; //最低价
    private double hPrice; //最高价钱
    private double lAge;
    private double hAge;
    private int pageIndex;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public double getlPrice() {
        return lPrice;
    }

    public void setlPrice(double lPrice) {
        this.lPrice = lPrice;
    }

    public double gethPrice() {
        return hPrice;
    }

    public void sethPrice(double hPrice) {
        this.hPrice = hPrice;
    }

    public double getlAge() {
        return lAge;
    }

    public void setlAge(double lAge) {
        this.lAge = lAge;
    }

    public double gethAge() {
        return hAge;
    }

    public void sethAge(double hAge) {
        this.hAge = hAge;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
