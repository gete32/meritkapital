package main.java.exchanger.controller;

import main.java.exchanger.dto.CoefficientDto;
import main.java.exchanger.dto.CurrencyCoefficientDto;
import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.dto.TimeSlotDto;
import main.java.exchanger.service.CoefficientService;
import main.java.exchanger.service.CurrencyService;
import main.java.exchanger.service.TimeSlotService;
import main.java.exchanger.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by root on 28.05.17.
 */
@RestController
@RequestMapping("/")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private CoefficientService coefficientService;

    @RequestMapping(method = RequestMethod.GET, value = "/json/{currencyName}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<CurrencyCoefficientDto> filtered(@PathVariable String currencyName,
                                                       @PathVariable String startDate,
                                                       @PathVariable String endDate) {

        final Collection<CurrencyDto> currencies = service.getAll();
        final Collection<TimeSlotDto> timeSlots = timeSlotService.getAll();

        CurrencyDto currencyDto = service.getCurrencyByName(currencyName);
        Collection<TimeSlotDto> timeSlotDtos =
                timeSlotService.getSlotsByDates(DateUtils.parse(startDate), DateUtils.parse(endDate));
        Collection<CoefficientDto> coefficientDtos =
                coefficientService.getCoefficientsByCurrencyIdAndSlotId(
                        currencyDto.getId(), timeSlotDtos.stream().map(TimeSlotDto::getId).collect(Collectors.toSet()));

        return coefficientService.convert(currencies, timeSlots, coefficientDtos);
    }
}
