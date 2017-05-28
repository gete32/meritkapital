package exchanger.service;

import main.java.exchanger.dto.CoefficientDto;
import main.java.exchanger.dto.CurrencyDto;
import main.java.exchanger.dto.TimeSlotDto;
import main.java.exchanger.entity.CurrencyPairPK;
import main.java.exchanger.service.CoefficientService;
import main.java.exchanger.service.CurrencyService;
import main.java.exchanger.service.TimeSlotService;
import main.java.exchanger.utils.DateUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by root on 27.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/applicationContext.xml",
        "classpath:/main/webapp/WEB-INF/dispatcher-servlet.xml"}
)
public class CurrencyServiceTest {

    private final static String CURRENCY_JSON_KEY = "currencies";
    private final static String TIME_SLOTS_JSON_KEY = "timeSlots";
    private final static String COEFFICIENT_JSON_KEY = "coefficient";
    private final static String TIME_SLOT_COEFFICIENT_JSON_KEY = "timeSlotCoefficients";
    private final static String CURRENCY_NAME_FIRST_JSON_KEY = "nameFirst";
    private final static String CURRENCY_NAME_SECOND_JSON_KEY = "nameSecond";
    private final static String START_DATE_JSON_KEY = "startDate";
    private final static String END_DATE_JSON_KEY = "endDate";

    private static Map<String, TimeSlotDto> timeSlotDtoMap = new HashMap<>();
    private static Map<String, CurrencyDto> currencyDtoMap = new HashMap<>();
    private static Collection<CoefficientDto> coefficients = new ArrayList<>();

    private JSONArray jsonArrayCurrency, jsonArrayTimeSlots, jsonArrayCoefficientTimeSlots;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private CoefficientService coefficientService;

    @Before
    public void readJson() throws IOException {
        final String jsonString = IOUtils.toString(this.getClass().getResourceAsStream("/currencies.txt"));
        final JSONObject json = new JSONObject(jsonString);
        jsonArrayCurrency = json.getJSONArray(CURRENCY_JSON_KEY);
        jsonArrayTimeSlots = json.getJSONArray(TIME_SLOTS_JSON_KEY);
        jsonArrayCoefficientTimeSlots = json.getJSONArray(TIME_SLOT_COEFFICIENT_JSON_KEY);
    }

    private Map<String, CurrencyDto> saveCurrencies(final JSONArray array){
        final Collection<CurrencyDto> currencyDtos = new ArrayList<>();

        array.forEach(e -> currencyDtos.add(new CurrencyDto(null, e.toString())));
        return currencyService.saveAll(currencyDtos).stream().collect(Collectors.toMap(CurrencyDto::getName, e -> e));
    }

    private Map<String, TimeSlotDto> saveTimeSlots(final JSONArray array){
        final Collection<TimeSlotDto> timeSlotDtos = new ArrayList<>();

        array.forEach(e -> {
            final JSONObject timeSlot = (JSONObject) e;
            final Date startDate = DateUtils.parse(timeSlot.getString(START_DATE_JSON_KEY));
            final Date endDate = DateUtils.parse(timeSlot.getString(END_DATE_JSON_KEY));
            timeSlotDtos.add(new TimeSlotDto(null, startDate, endDate));
        });
        return timeSlotService.saveAll(timeSlotDtos).stream().collect(Collectors.toMap(
                e -> DateUtils.format(e.getStartDate()) + DateUtils.format(e.getEndDate()), e -> e, (e1, e2) -> e1));
    }

    private Collection<CoefficientDto> saveCoefficients(final Map<String, CurrencyDto> currencies,
                                  final Map<String, TimeSlotDto> timeSlots,
                                  final JSONArray coefficients){
        final Collection<CoefficientDto> coefficientDtos = new ArrayList<>();

        coefficients.forEach(e->{
            final JSONObject coefficient = (JSONObject) e;
            final String startDateTimeSlot = coefficient.getString(START_DATE_JSON_KEY);
            final String endDateTimeSlot = coefficient.getString(END_DATE_JSON_KEY);
            final CoefficientDto coefficientDto = new CoefficientDto(
                    currencies.get(coefficient.getString(CURRENCY_NAME_FIRST_JSON_KEY)).getId(),
                    currencies.get(coefficient.getString(CURRENCY_NAME_SECOND_JSON_KEY)).getId(),
                    timeSlots.get(startDateTimeSlot + endDateTimeSlot).getId(),
                    coefficient.getBigDecimal(COEFFICIENT_JSON_KEY)
            );
            coefficientDtos.add(coefficientDto);
        });
        return coefficientService.saveAll(coefficientDtos);
    }

    @Test
    public void currencyTest() {
        currencyDtoMap = saveCurrencies(jsonArrayCurrency);
        currencyDtoMap.values().forEach(e ->
                Assert.assertEquals(e.getName(), currencyService.get(e.getId()).getName()));

        timeSlotDtoMap = saveTimeSlots(jsonArrayTimeSlots);
        timeSlotDtoMap.values().forEach(e -> {
                    final Integer id = e.getId();
                    Assert.assertEquals(e.getStartDate(), timeSlotService.get(id).getStartDate());
                    Assert.assertEquals(e.getEndDate(), timeSlotService.get(id).getEndDate());
                }
        );

        saveCoefficients(currencyDtoMap, timeSlotDtoMap, jsonArrayCoefficientTimeSlots);
        coefficients.forEach(e->{
            final CurrencyPairPK id =
                    new CurrencyPairPK(e.getFirstCurrencyId(), e.getSecondCurrencyId(), e.getTimeSlotDtoId());
            Assert.assertEquals(e.getCoefficient(), coefficientService.get(id).getCoefficient());
        });
    }

    @After
    public void remove() {
        coefficientService.deleteAll(coefficients);
        timeSlotService.deleteAll(timeSlotDtoMap.values());
        currencyService.deleteAll(currencyDtoMap.values());
    }
}
