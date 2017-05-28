package main.java.exchanger.converter;

import main.java.exchanger.dto.StatisticDto;
import main.java.exchanger.entity.Statistic;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 29.05.17.
 */
public class StatisticConverter implements Converter<Statistic, StatisticDto> {

    @Override
    public StatisticDto convert(Statistic source) {
        if (source == null) return null;

        final StatisticDto statisticDto = new StatisticDto();
        statisticDto.setId(source.getId());
        statisticDto.setTimeStamp(source.getTimeStamp());
        statisticDto.setIp(source.getRequestInfo().getIp());
        statisticDto.setCount(source.getRequestInfo().getCount());
        return statisticDto;
    }
}
