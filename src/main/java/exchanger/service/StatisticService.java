package main.java.exchanger.service;

import main.java.exchanger.dto.StatisticDto;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * Created by root on 29.05.17.
 */
public class StatisticService implements Service<Integer, StatisticDto>{

    @Override
    public StatisticDto get(Integer id) {
        return null;
    }

    @Override
    public Collection<StatisticDto> getAll() {
        return null;
    }

    @Override
    public void deleteAll(Collection<StatisticDto> elements) {

    }

    @Override
    public Collection<StatisticDto> saveAll(Collection<StatisticDto> elements) {
        if (CollectionUtils.isEmpty(elements)) return null;
        return null;
    }
}
