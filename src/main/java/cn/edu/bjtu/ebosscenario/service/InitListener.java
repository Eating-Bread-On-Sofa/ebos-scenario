package cn.edu.bjtu.ebosscenario.service;

import cn.edu.bjtu.ebosscenario.dao.ScenarioMessageRepo;
import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.domain.ScenarioMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@Order(1)
public class InitListener implements ApplicationRunner {
    @Autowired
    ScenarioService scenarioService;
    @Autowired
    MqFactory mqFactory;
    @Autowired
    ScenarioMessageRepo scenarioMessageRepo;
    @Value("${mq}")
    private String name;

    @Override
    public void run(ApplicationArguments arguments) {
        new Thread(() -> {
            MqConsumer mqConsumer = mqFactory.createConsumer("notice");
            while (true) {
                JSONObject msg = JSON.parseObject(mqConsumer.subscribe());
                System.out.println("收到：" + msg);
                ScenarioMessage scenarioMessage = JSONObject.toJavaObject(msg,ScenarioMessage.class);
                scenarioMessage.setCreated(new Date());
                List<Scenario> scenarioList = scenarioService.findByRules(scenarioMessage.getSource());
                scenarioMessage.setScenarioList(scenarioList);
                scenarioMessageRepo.save(scenarioMessage);
            }
        }).start();
    }
}
