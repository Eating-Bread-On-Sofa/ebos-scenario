package cn.edu.bjtu.ebosscenario.controller;

import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.service.ScenarioService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/api/scenario")
@RestController
public class ScenarioController {
    @Autowired
    ScenarioService scenarioService;
    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin
    @PostMapping
    public boolean add(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray content = jsonObject.getJSONArray("content");
        Scenario scenario = new Scenario();
        scenario.setName(name);
        scenario.setContent(content);
        return scenarioService.addScenario(scenario);
    }

    @CrossOrigin
    @DeleteMapping("/name/{name}")
    public boolean delete(@PathVariable String name){
        return scenarioService.deleteByName(name);
    }

    @CrossOrigin
    @PutMapping
    public void change(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name");
        JSONArray content = jsonObject.getJSONArray("content");
        Scenario scenario = new Scenario();
        scenario.setName(name);
        scenario.setContent(content);
        scenarioService.changeScenario(scenario);
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
    @GetMapping("/status/{name}")
    public JSONArray getStatus(@PathVariable String name){
        JSONArray readings = new JSONArray();
        Scenario scenario = scenarioService.findByName(name);
        JSONArray content = scenario.getContent();
        for (int i = 0; i < content.size(); i++) {
            JSONObject gateway = content.getJSONObject(i);
            String ip = gateway.getString("gatewayIP");
            JSONArray commands = gateway.getJSONArray("commands");
            for (int j = 0; j < commands.size(); j++) {
                JSONObject command = commands.getJSONObject(j);
                String deviceName = command.getString("deviceName");
                String commandName = command.getString("commandName");
                String url = "http://"+ip+":48082/api/v1/device/name/"+deviceName+"/command/"+commandName;
                JSONObject result = restTemplate.getForObject(url,JSONObject.class);
                readings.addAll(result.getJSONArray("readings"));
            }
        }
        return readings;
    }
}
