package cn.edu.bjtu.ebosscenario.dao;

import cn.edu.bjtu.ebosscenario.domain.Scenario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends MongoRepository<Scenario, String> {
    Scenario findScenarioByName(String name);
    void deleteScenarioByName(String name);
    @Override
    List<Scenario> findAll();
}
