package main.java.exchanger.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by root on 27.05.17.
 */
@Entity
@Table (name = "coefficient")
public class Coefficient implements Serializable{

    @EmbeddedId
    private CurrencyPairPK currencyPairPK;

    @Column (name = "coefficient")
    private BigDecimal coefficient;

    public Coefficient() {
    }

    public Coefficient(CurrencyPairPK currencyPairPK, BigDecimal coefficient) {
        this.currencyPairPK = currencyPairPK;
        this.coefficient = coefficient;
    }

    public Coefficient(final Integer currencyFirstId, Integer currencySecondId, Integer timeSlotId, BigDecimal coefficient) {
        this(new CurrencyPairPK(currencyFirstId, currencySecondId, timeSlotId), coefficient);
    }

    public CurrencyPairPK getCurrencyPairPK() {
        return currencyPairPK;
    }

    public void setCurrencyPairPK(CurrencyPairPK currencyPairPK) {
        this.currencyPairPK = currencyPairPK;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
