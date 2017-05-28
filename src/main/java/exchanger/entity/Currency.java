package main.java.exchanger.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by root on 27.05.17.
 */
@Entity
@Table(name = "currency")
public class Currency implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, length = 3)
    private String name;

    public Currency() {
    }

    public Currency(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
