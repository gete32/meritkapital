package main.java.exchanger.repository;

import main.java.exchanger.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 28.05.17.
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

}
