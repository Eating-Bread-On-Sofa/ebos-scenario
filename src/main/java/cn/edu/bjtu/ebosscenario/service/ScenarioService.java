package cn.edu.bjtu.ebosscenario.service;

import cn.edu.bjtu.ebosscenario.domain.Scenario;

import java.util.Date;
import java.util.List;

public interface ScenarioService {
    boolean addScenario(Scenario scenario);
    Scenario findByName(String name);
    List<Scenario> findAll();
    boolean deleteByName(String name);
    void changeScenario(Scenario scenario);
    List<Scenario> findByCreatedBetween(Date start, Date end);
    List<Scenario> findByRules(String rule);
}
