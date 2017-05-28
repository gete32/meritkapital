package main.java.exchanger.converter;

import main.java.exchanger.dto.TimeSlotDto;
import main.java.exchanger.entity.TimeSlot;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 28.05.17.
 */
public class TimeSlotConverter implements Converter<TimeSlot, TimeSlotDto> {

    @Override
    public TimeSlotDto convert(TimeSlot source) {
        if (source == null) return null;

        final TimeSlotDto timeSlotDto = new TimeSlotDto();
        timeSlotDto.setId(source.getId());
        timeSlotDto.setStartDate(source.getStartDate());
        timeSlotDto.setEndDate(source.getEndDate());
        return timeSlotDto;
    }
}
