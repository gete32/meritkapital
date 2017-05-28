package main.java.exchanger.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by root on 27.05.17.
 */
@Entity
@Table(name = "statistic")
public class Statistic implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "time_stamp")
    private Date timeStamp;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "ip")
    private RequestInfo requestInfo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "coefficient_statistic")
    private Collection<Coefficient> coefficients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public Collection<Coefficient> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Collection<Coefficient> coefficients) {
        this.coefficients = coefficients;
    }
}
