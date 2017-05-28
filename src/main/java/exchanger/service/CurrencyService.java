package main.java.exchanger.service;

import main.java.exchanger.converter.CurrencyConverter;
import main.java.exchanger.converter.ReverseCurrencyConverter;
import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.entity.Currency;
import main.java.exchanger.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by root on 27.05.17.
 */
public class CurrencyService implements Service<Integer, CurrencyDto> {

    private Collection<CurrencyDto> currencyDtos = null;
    private Map<String, CurrencyDto> currencyMap = null;

    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyConverter converter;

    @Autowired
    private ReverseCurrencyConverter reverseCurrencyConverter;

    @Override
    public CurrencyDto get(Integer id) {
        if (id == null) return null;

        return converter.convert(repository.findOne(id));
    }

    @Override
    public Collection<CurrencyDto> getAll() {
        if (currencyDtos != null) return currencyDtos;

        Collection<Currency> currencies = repository.findAll();
        if (CollectionUtils.isEmpty(currencies)) return null;
        currencyDtos = currencies.stream().map(converter::convert).collect(Collectors.toList());
        return currencyDtos;
    }

    @Override
    public void deleteAll(Collection<CurrencyDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        repository.delete(elements.stream().map(reverseCurrencyConverter::convert).collect(Collectors.toList()));
    }

    @Override
    public Collection<CurrencyDto> saveAll(Collection<CurrencyDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return null;

        final Collection<Currency> currencies
                = elements.stream().map(e -> new Currency(e.getName())).collect(Collectors.toList());
        return repository.save(currencies).stream().map(converter::convert).collect(Collectors.toList());
    }


    private Map<String, CurrencyDto> getCurrencyMap(){
        if (currencyMap != null) return currencyMap;

        final Collection<CurrencyDto> currencyDtos = getAll();
        if (CollectionUtils.isEmpty(currencyDtos)) return null;

        currencyMap = currencyDtos.stream().collect(Collectors.toMap(CurrencyDto::getName, e -> e, (e1, e2) -> e1));
        return currencyMap;
    }

    public CurrencyDto getCurrencyByName(final String name) {
        final Map<String, CurrencyDto> map = getCurrencyMap();
        return map == null ? null : map.get(name);
    }

}
