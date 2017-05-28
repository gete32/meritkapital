package main.java.exchanger.service;

import main.java.exchanger.converter.TimeSlotConverter;
import main.java.exchanger.dto.TimeSlotDto;
import main.java.exchanger.entity.TimeSlot;
import main.java.exchanger.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by root on 28.05.17.
 */
public class TimeSlotService implements Service<Integer, TimeSlotDto> {

    private Collection<TimeSlotDto> timeSlotDtos = null;

    @Autowired
    private TimeSlotRepository repository;

    @Autowired
    private TimeSlotConverter converter;

    @Override
    public TimeSlotDto get(Integer id) {
        if (id == null) return null;

        return converter.convert(repository.findOne(id));
    }

    @Override
    public Collection<TimeSlotDto> getAll() {
        if (timeSlotDtos != null) return timeSlotDtos;

        final Collection<TimeSlot> timeSlots = repository.findAll();

        timeSlotDtos = CollectionUtils.isEmpty(timeSlots) ? null :
                timeSlots.stream().map(converter::convert).collect(Collectors.toList());

        return timeSlotDtos;
    }

    @Override
    public void deleteAll(Collection<TimeSlotDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        repository.delete(elements.stream().map(e -> new TimeSlot(
                        e.getId(),
                        e.getStartDate(),
                        e.getEndDate())
        ).collect(Collectors.toList()));
    }

    @Override
    public Collection<TimeSlotDto> saveAll(Collection<TimeSlotDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return null;

        final Collection<TimeSlot> timeSlots = elements.stream().map(e ->
                new TimeSlot(null, e.getStartDate(), e.getEndDate())).collect(Collectors.toList());

        return repository.save(timeSlots).stream().map(converter::convert).collect(Collectors.toList());
    }

    public Collection<TimeSlotDto> getSlotsByDates(final Date startDate, final Date endDate) {
        if (startDate == null || endDate == null) return null;

        final Collection<TimeSlotDto> timeSlots = this.getAll();
        return timeSlots.stream()
                .filter(e -> !startDate.after(e.getStartDate()))
                .filter(e -> !endDate.before(e.getEndDate()))
                .collect(Collectors.toList());
    }
}
