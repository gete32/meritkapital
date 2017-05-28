package main.java.exchanger.dto;

import java.math.BigDecimal;

/**
 * Created by root on 28.05.17.
 */
public class CoefficientDto implements Dto {

    private Integer firstCurrencyId;

    private Integer secondCurrencyId;

    private Integer timeSlotDtoId;

    private BigDecimal coefficient;

    public CoefficientDto() {
    }

    public CoefficientDto(Integer firstCurrencyId, Integer secondCurrencyId, Integer timeSlotDtoId, BigDecimal coefficient) {
        this.firstCurrencyId = firstCurrencyId;
        this.secondCurrencyId = secondCurrencyId;
        this.timeSlotDtoId = timeSlotDtoId;
        this.coefficient = coefficient;
    }

    public Integer getFirstCurrencyId() {
        return firstCurrencyId;
    }

    public void setFirstCurrencyId(Integer firstCurrencyId) {
        this.firstCurrencyId = firstCurrencyId;
    }

    public Integer getSecondCurrencyId() {
        return secondCurrencyId;
    }

    public void setSecondCurrencyId(Integer secondCurrencyId) {
        this.secondCurrencyId = secondCurrencyId;
    }

    public Integer getTimeSlotDtoId() {
        return timeSlotDtoId;
    }

    public void setTimeSlotDtoId(Integer timeSlotDtoId) {
        this.timeSlotDtoId = timeSlotDtoId;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
