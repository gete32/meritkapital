package main.java.exchanger.converter;

import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.entity.Currency;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 27.05.17.
 */
public class CurrencyConverter implements Converter<Currency, CurrencyDto> {

    @Override
    public CurrencyDto convert(Currency source) {

        if (source == null) return null;

        final CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(source.getId());
        currencyDto.setName(source.getName());
        return currencyDto;
    }
}
