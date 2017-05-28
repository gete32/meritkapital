package main.java.exchanger.repository;

import main.java.exchanger.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 29.05.17.
 */
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
}
