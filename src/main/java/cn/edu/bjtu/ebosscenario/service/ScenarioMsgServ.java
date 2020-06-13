package cn.edu.bjtu.ebosscenario.service;

import cn.edu.bjtu.ebosscenario.domain.ScenarioMessage;

import java.util.Date;
import java.util.List;

public interface ScenarioMsgServ {
    List<ScenarioMessage> findByCreatedBetween(Date start, Date end);
}
