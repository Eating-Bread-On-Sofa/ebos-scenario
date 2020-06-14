package cn.edu.bjtu.ebosscenario.controller;


import cn.edu.bjtu.ebosscenario.domain.Command;
import cn.edu.bjtu.ebosscenario.domain.Gateway;
import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.domain.ScenarioMessage;
import cn.edu.bjtu.ebosscenario.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Api(tags = "场景管理")
@RequestMapping("/api/scenario")
@RestController
public class ScenarioController {
    @Autowired
    ScenarioService scenarioService;
    @Autowired
    ScenarioMsgServ scenarioMsgServ;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LogService logService;

    @CrossOrigin
    @PostMapping
    public boolean add(@RequestBody Scenario scenario) {
        logService.info(null,"添加了新场景："+scenario.getName());
        return scenarioService.addScenario(scenario);
    }

    @CrossOrigin
    @DeleteMapping("/name/{name}")
    public boolean delete(@PathVariable String name){
        logService.info(null,"删除场景："+name);
        return scenarioService.deleteByName(name);
    }

    @CrossOrigin
    @PutMapping
    public void change(@RequestBody Scenario scenario){
        scenarioService.changeScenario(scenario);
        logService.info(null,"调整了场景："+scenario.getName());
    }

    @CrossOrigin
    @GetMapping("/name/{name}")
    public Scenario findOneScenario(@PathVariable String name){
        return scenarioService.findByName(name);
    }

    @CrossOrigin
    @GetMapping
    public List<Scenario> findAll(){
        return scenarioService.findAll();
    }

    @CrossOrigin
    @GetMapping("/days")
    public JSONArray findRecent(@RequestParam int days){
        JSONArray jsonArray = new JSONArray();
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        for (int i = 0; i < days; i++) {
            calendar.add(Calendar.DATE, -1);
            Date start = calendar.getTime();
            List<Scenario> scenarios = scenarioService.findByCreatedBetween(start,end);
            JSONArray details = new JSONArray();
            details.addAll(scenarios);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("startDate",start);
            jsonObject.put("endDate",end);
            jsonObject.put("details",details);
            jsonObject.put("count",scenarios.size());
            jsonArray.add(jsonObject);
            end = start;
        }
        return jsonArray;
    }

    @CrossOrigin
    @GetMapping("/status/{name}")
    public JSONArray getStatus(@PathVariable String name){
        JSONArray readings = new JSONArray();
        Scenario scenario = scenarioService.findByName(name);
        Gateway[] content = scenario.getContent();
        for (Gateway gateway:content) {
            String ip = gateway.getGatewayIP();
            Command[] commands = gateway.getCommands();
            for (Command command:commands) {
                String deviceName = command.getDeviceName();
                String commandName = command.getCommandName();
                String url = "http://"+ip+":48082/api/v1/device/name/"+deviceName+"/command/"+commandName;
                JSONObject result = restTemplate.getForObject(url,JSONObject.class);
                readings.addAll(result.getJSONArray("readings"));
            }
        }
        return readings;
    }

    @CrossOrigin
    @GetMapping("/notice")
    public List<ScenarioMessage> getNotice(@RequestParam int days){
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DATE, -days);
        Date start = calendar.getTime();
        return scenarioMsgServ.findByCreatedBetween(start,end);
    }
}
