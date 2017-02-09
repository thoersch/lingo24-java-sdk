package com.thoersch.lingo24.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private CurrencyCode currencyCode;
    private String totalWoVatWDiscount;
    private String totalWoVatWoDiscount;
    private String totalWVatWDiscount;
    private String totalWVatWoDiscount;

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTotalWoVatWDiscount() {
        return totalWoVatWDiscount;
    }

    public void setTotalWoVatWDiscount(String totalWoVatWDiscount) {
        this.totalWoVatWDiscount = totalWoVatWDiscount;
    }

    public String getTotalWoVatWoDiscount() {
        return totalWoVatWoDiscount;
    }

    public void setTotalWoVatWoDiscount(String totalWoVatWoDiscount) {
        this.totalWoVatWoDiscount = totalWoVatWoDiscount;
    }

    public String getTotalWVatWDiscount() {
        return totalWVatWDiscount;
    }

    public void setTotalWVatWDiscount(String totalWVatWDiscount) {
        this.totalWVatWDiscount = totalWVatWDiscount;
    }

    public String getTotalWVatWoDiscount() {
        return totalWVatWoDiscount;
    }

    public void setTotalWVatWoDiscount(String totalWVatWoDiscount) {
        this.totalWVatWoDiscount = totalWVatWoDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price = (Price) o;

        if (currencyCode != price.currencyCode) return false;
        if (totalWoVatWDiscount != null ? !totalWoVatWDiscount.equals(price.totalWoVatWDiscount) : price.totalWoVatWDiscount != null)
            return false;
        if (totalWoVatWoDiscount != null ? !totalWoVatWoDiscount.equals(price.totalWoVatWoDiscount) : price.totalWoVatWoDiscount != null)
            return false;
        if (totalWVatWDiscount != null ? !totalWVatWDiscount.equals(price.totalWVatWDiscount) : price.totalWVatWDiscount != null)
            return false;
        return !(totalWVatWoDiscount != null ? !totalWVatWoDiscount.equals(price.totalWVatWoDiscount) : price.totalWVatWoDiscount != null);

    }

    @Override
    public int hashCode() {
        int result = currencyCode != null ? currencyCode.hashCode() : 0;
        result = 31 * result + (totalWoVatWDiscount != null ? totalWoVatWDiscount.hashCode() : 0);
        result = 31 * result + (totalWoVatWoDiscount != null ? totalWoVatWoDiscount.hashCode() : 0);
        result = 31 * result + (totalWVatWDiscount != null ? totalWVatWDiscount.hashCode() : 0);
        result = 31 * result + (totalWVatWoDiscount != null ? totalWVatWoDiscount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "currencyCode=" + currencyCode +
                ", totalWoVatWDiscount='" + totalWoVatWDiscount + '\'' +
                ", totalWoVatWoDiscount='" + totalWoVatWoDiscount + '\'' +
                ", totalWVatWDiscount='" + totalWVatWDiscount + '\'' +
                ", totalWVatWoDiscount='" + totalWVatWoDiscount + '\'' +
                '}';
    }
}
