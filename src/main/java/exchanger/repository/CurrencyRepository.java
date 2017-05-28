package main.java.exchanger.repository;

import main.java.exchanger.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 27.05.17.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
