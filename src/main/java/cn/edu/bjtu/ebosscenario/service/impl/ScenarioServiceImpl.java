package cn.edu.bjtu.ebosscenario.service.impl;

import cn.edu.bjtu.ebosscenario.dao.ScenarioRepository;
import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    @Autowired
    ScenarioRepository scenarioRepository;
    @Override
    public boolean addScenario(Scenario scenario) {
        scenario.setCreated(new Date());
        Scenario scenarioTemp = scenarioRepository.findScenarioByName(scenario.getName());
        if (scenarioTemp == null) {
            scenarioRepository.save(scenario);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Scenario findByName(String name) {
        return scenarioRepository.findScenarioByName(name);
    }

    @Override
    public List<Scenario> findAll() {
        return scenarioRepository.findAll();
    }

    @Override
    public boolean deleteByName(String name) {
        Scenario scenarioTmp = scenarioRepository.findScenarioByName(name);
        if (scenarioTmp == null) {
            return false;
        } else {
            scenarioRepository.deleteScenarioByName(name);
            return true;
        }
    }

    @Override
    public void changeScenario(Scenario scenario) {
        scenarioRepository.save(scenario);
    }

    @Override
    public List<Scenario> findByCreatedBetween(Date start, Date end){
        return scenarioRepository.findByCreatedBetween(start, end);
    }

    public List<Scenario> findByRules(String rule){
        List<Scenario> res = new LinkedList<>();
        List<Scenario> scenarioList = scenarioRepository.findAll();
        for (Scenario scenario:scenarioList) {
            Set<String> rules = scenario.getRules();
            if (rules.contains(rule)){
                res.add(scenario);
            }
        }
        return res;
    }
}
