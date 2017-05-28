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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by root on 27.05.17.
 */
@Controller
@RequestMapping("/")
public class CurrencyViewController {

    private final static String TABLE = "currencyTable";
    private final static String INDEX = "index";

    @Autowired
    private CurrencyService service;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private CoefficientService coefficientService;

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return INDEX;
    }

    @RequestMapping(method = RequestMethod.GET, value = "all")
    public String all(Model model) {

        final Collection<CurrencyDto> currencies = service.getAll();
        final Collection<TimeSlotDto> timeSlots = timeSlotService.getAll();
        final Collection<CoefficientDto> coefficients = coefficientService.getAll();

        final Collection<CurrencyCoefficientDto> currencyCoefficientDtos =
                coefficientService.convert(currencies, timeSlots, coefficients);

        model.addAttribute("currencyCoefficientDtos", currencyCoefficientDtos);
        model.addAttribute("currencies", currencies);
        model.addAttribute("startSlots", timeSlots.stream().map(e -> DateUtils.format(e.getStartDate())).collect(Collectors.toList()));
        model.addAttribute("endSlots", timeSlots.stream().map(e -> DateUtils.format(e.getEndDate())).collect(Collectors.toList()));

        return TABLE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter/{currencyName}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String filtered(@PathVariable String currencyName,
                                                       @PathVariable String startDate,
                                                       @PathVariable String endDate,
                                                       Model model) {

        final Collection<CurrencyDto> currencies = service.getAll();
        final Collection<TimeSlotDto> timeSlots = timeSlotService.getAll();

        CurrencyDto currencyDto = service.getCurrencyByName(currencyName);
        Collection<TimeSlotDto> timeSlotDtos =
                timeSlotService.getSlotsByDates(DateUtils.parse(startDate), DateUtils.parse(endDate));
        Collection<CoefficientDto> coefficientDtos =
                coefficientService.getCoefficientsByCurrencyIdAndSlotId(
                        currencyDto.getId(), timeSlotDtos.stream().map(TimeSlotDto::getId).collect(Collectors.toSet()));

        model.addAttribute("currencyCoefficientDtos", coefficientService.convert(currencies, timeSlots, coefficientDtos));
        model.addAttribute("currencies", currencies);
        model.addAttribute("startSlots", timeSlots.stream().map(e -> DateUtils.format(e.getStartDate())).collect(Collectors.toList()));
        model.addAttribute("endSlots", timeSlots.stream().map(e -> DateUtils.format(e.getEndDate())).collect(Collectors.toList()));
        return TABLE;
    }
}