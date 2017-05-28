package main.java.exchanger.dto;

/**
 * Created by root on 27.05.17.
 */
public class CurrencyDto implements Dto {

    private Integer id;
    private String name;

    public CurrencyDto() {
    }

    public CurrencyDto(Integer id, String name) {
        this.id = id;
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
