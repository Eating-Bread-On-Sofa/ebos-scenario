package cn.edu.bjtu.ebosscenario.dao;

import cn.edu.bjtu.ebosscenario.domain.ScenarioMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScenarioMessageRepo extends MongoRepository<ScenarioMessage, String> {
    List<ScenarioMessage> findByCreatedBetween(Date start, Date end);
}
