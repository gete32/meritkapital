package main.java.exchanger.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by root on 27.05.17.
 */
@Entity
@Table (name = "request_info")
public class RequestInfo implements Serializable{

    @Id
    @Column (name = "ip")
    private String ip;

    @Column (name = "count")
    private Integer count;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
