package main.java.exchanger.converter;

import main.java.exchanger.dto.CoefficientDto;
import main.java.exchanger.entity.Coefficient;
import main.java.exchanger.entity.CurrencyPairPK;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 28.05.17.
 */
public class CoefficientConverter implements Converter<Coefficient, CoefficientDto> {

    @Override
    public CoefficientDto convert(Coefficient source) {
        if (source == null) return null;

        final CurrencyPairPK currencyPairPK = source.getCurrencyPairPK();
        if (currencyPairPK == null) return null;

        final CoefficientDto coefficientDto = new CoefficientDto();
        coefficientDto.setFirstCurrencyId(currencyPairPK.getCurrencyFirst());
        coefficientDto.setSecondCurrencyId(currencyPairPK.getCurrencySecond());
        coefficientDto.setTimeSlotDtoId(currencyPairPK.getTimeSlot());
        coefficientDto.setCoefficient(source.getCoefficient());
        return coefficientDto;
    }
}
