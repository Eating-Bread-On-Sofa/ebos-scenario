package cn.edu.bjtu.ebosscenario.service.impl;

import cn.edu.bjtu.ebosscenario.dao.ScenarioRepository;
import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    @Autowired
    ScenarioRepository scenarioRepository;
    @Override
    public boolean addScenario(Scenario scenario) {
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
}
