package main.java.exchanger.repository;

import main.java.exchanger.entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 29.05.17.
 */
public interface RequestInfoRepository extends JpaRepository<RequestInfo, Integer> {
}
