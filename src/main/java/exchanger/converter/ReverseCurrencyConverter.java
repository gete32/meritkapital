package main.java.exchanger.converter;

import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.entity.Currency;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 27.05.17.
 */
public class ReverseCurrencyConverter implements Converter<CurrencyDto, Currency> {

    @Override
    public Currency convert(CurrencyDto source) {
        if (source == null) return null;

        final Currency currency = new Currency();
        currency.setId(source.getId());
        currency.setName(source.getName());
        return currency;
    }
}
