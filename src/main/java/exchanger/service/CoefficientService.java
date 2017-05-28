package main.java.exchanger.service;

import main.java.exchanger.converter.CoefficientConverter;
import main.java.exchanger.dto.CoefficientDto;
import main.java.exchanger.dto.CurrencyCoefficientDto;
import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.dto.TimeSlotDto;
import main.java.exchanger.entity.Coefficient;
import main.java.exchanger.entity.CurrencyPairPK;
import main.java.exchanger.repository.CoefficientRepository;
import main.java.exchanger.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by root on 28.05.17.
 */
public class CoefficientService implements Service<CurrencyPairPK, CoefficientDto> {

    private Collection<CoefficientDto> coefficientDtos = null;

    @Autowired
    private CoefficientRepository repository;

    @Autowired
    private CoefficientConverter converter;

    @Override
    public CoefficientDto get(CurrencyPairPK id) {
        return null;
    }

    @Override
    public Collection<CoefficientDto> getAll() {
        if (coefficientDtos != null) return coefficientDtos;

        final Collection<Coefficient> coefficients = repository.findAll();
        coefficientDtos = CollectionUtils.isEmpty(coefficients) ? null :
                coefficients.stream().map(converter::convert).collect(Collectors.toList());
        return coefficientDtos;
    }

    @Override
    public void deleteAll(Collection<CoefficientDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        repository.delete(elements.stream().map(e ->
                new Coefficient(
                        e.getFirstCurrencyId(),
                        e.getSecondCurrencyId(),
                        e.getTimeSlotDtoId(),
                        e.getCoefficient())
        ).collect(Collectors.toList()));
    }

    @Override
    public Collection<CoefficientDto> saveAll(Collection<CoefficientDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return null;

        final Collection<Coefficient> coefficients = elements.stream().map(e ->
                        new Coefficient(
                                e.getFirstCurrencyId(),
                                e.getSecondCurrencyId(),
                                e.getTimeSlotDtoId(),
                                e.getCoefficient()
                        )
        ).collect(Collectors.toList());
        return repository.save(coefficients).stream().map(converter::convert).collect(Collectors.toList());
    }

    public Collection<CoefficientDto> getCoefficientsByCurrencyIdAndSlotId(final Integer currencyId,
                                                                            final Collection<Integer> slotIds){

        if (currencyId == null || CollectionUtils.isEmpty(slotIds)) return null;

        Collection<CoefficientDto> coefficientDtos = getAll();

        return coefficientDtos.stream()
                .filter(e -> currencyId.equals(e.getFirstCurrencyId()) || currencyId.equals(e.getSecondCurrencyId()))
                .filter(e -> slotIds.contains(e.getTimeSlotDtoId()))
                .collect(Collectors.toList());
    }

    private Collection<CurrencyCoefficientDto> collect(final Map<Integer, String> currencyDtoMap,
                                                       final Map<Integer, TimeSlotDto> timeSlotDtoMap,
                                                       final Collection<CoefficientDto> coefficientDtos) {
        return coefficientDtos.stream().map(e -> {
            final TimeSlotDto timeSlotDto = timeSlotDtoMap.get(e.getTimeSlotDtoId());
            return new CurrencyCoefficientDto(currencyDtoMap.get(e.getFirstCurrencyId()),
                    currencyDtoMap.get(e.getSecondCurrencyId()),
                    DateUtils.format(timeSlotDto.getStartDate()),
                    DateUtils.format(timeSlotDto.getEndDate()),
                    e.getCoefficient());
        }).collect(Collectors.toList());
    }

    public Collection<CurrencyCoefficientDto> convert(final Collection<CurrencyDto> currencies,
                                                       final Collection<TimeSlotDto> timeSlots,
                                                       final Collection<CoefficientDto> coefficients){
        if (CollectionUtils.isEmpty(currencies) ||
                CollectionUtils.isEmpty(timeSlots) || CollectionUtils.isEmpty(coefficients)) return null;

        return collect(
                currencies.stream().collect(Collectors.toMap(CurrencyDto::getId, CurrencyDto::getName, (e1, e2) -> e1)),
                timeSlots.stream().collect(Collectors.toMap(TimeSlotDto::getId, e -> e, (e1, e2) -> e1)),
                coefficients
        );
    }
}
