package cn.edu.bjtu.ebosscenario.service.impl;

import cn.edu.bjtu.ebosscenario.dao.ScenarioMessageRepo;
import cn.edu.bjtu.ebosscenario.domain.ScenarioMessage;
import cn.edu.bjtu.ebosscenario.service.ScenarioMsgServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScenarioMsgServImpl implements ScenarioMsgServ {
    @Autowired
    ScenarioMessageRepo scenarioMessageRepo;

    @Override
    public List<ScenarioMessage> findByCreatedBetween(Date start, Date end){
        return scenarioMessageRepo.findByCreatedBetween(start,end);
    }
}
