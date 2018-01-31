package study.common;

public enum BrandPrice {
    Axi(27, "A"),
    Qxi(31, "Q"),
    Txi(46, "T"),
    Sxi(79, "S");

    BrandPrice(double value, String description) {
        this.value = value;
        this.description = description;
    }

    private Double value;
    private String description;

    public Double getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
