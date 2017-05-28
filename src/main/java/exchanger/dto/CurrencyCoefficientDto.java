package main.java.exchanger.dto;

import java.math.BigDecimal;

/**
 * Created by root on 28.05.17.
 */
public class CurrencyCoefficientDto implements Dto {

    private String firstCurrencyName;

    private String secondCurrencyName;

    private String startDate;

    private String endDate;

    private BigDecimal coefficient;

    public CurrencyCoefficientDto() {
    }

    public CurrencyCoefficientDto(String firstCurrencyName, String secondCurrencyName, String startDate, String endDate, BigDecimal coefficient) {
        this.firstCurrencyName = firstCurrencyName;
        this.secondCurrencyName = secondCurrencyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coefficient = coefficient;
    }

    public String getFirstCurrencyName() {
        return firstCurrencyName;
    }

    public void setFirstCurrencyName(String firstCurrencyName) {
        this.firstCurrencyName = firstCurrencyName;
    }

    public String getSecondCurrencyName() {
        return secondCurrencyName;
    }

    public void setSecondCurrencyName(String secondCurrencyName) {
        this.secondCurrencyName = secondCurrencyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
