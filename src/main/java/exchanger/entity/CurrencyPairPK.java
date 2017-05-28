package main.java.exchanger.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by root on 27.05.17.
 */
@Embeddable
public class CurrencyPairPK implements Serializable{

    private Integer currencyFirst;

    private Integer currencySecond;

    private Integer timeSlot;

    public CurrencyPairPK() {
    }

    public CurrencyPairPK(Integer currencyFirst, Integer currencySecond, Integer timeSlot) {
        this.currencyFirst = currencyFirst;
        this.currencySecond = currencySecond;
        this.timeSlot = timeSlot;
    }

    public Integer getCurrencyFirst() {
        return currencyFirst;
    }

    public void setCurrencyFirst(Integer currencyFirst) {
        this.currencyFirst = currencyFirst;
    }

    public Integer getCurrencySecond() {
        return currencySecond;
    }

    public void setCurrencySecond(Integer currencySecond) {
        this.currencySecond = currencySecond;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyPairPK that = (CurrencyPairPK) o;

        if (!currencyFirst.equals(that.currencyFirst)) return false;
        if (!currencySecond.equals(that.currencySecond)) return false;
        return timeSlot.equals(that.timeSlot);
    }

    @Override
    public int hashCode() {
        int result = currencyFirst.hashCode();
        result = 31 * result + currencySecond.hashCode();
        result = 31 * result + timeSlot.hashCode();
        return result;
    }
}
