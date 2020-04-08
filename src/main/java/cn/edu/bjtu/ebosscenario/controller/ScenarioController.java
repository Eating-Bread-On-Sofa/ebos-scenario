package cn.edu.bjtu.ebosscenario.controller;

import cn.edu.bjtu.ebosscenario.domain.Scenario;
import cn.edu.bjtu.ebosscenario.service.ScenarioService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/scenario")
@RestController
public class ScenarioController {
    @Autowired
    ScenarioService scenarioService;

    @PostMapping
    public boolean add(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray content = jsonObject.getJSONArray("content");
        Scenario scenario = new Scenario();
        scenario.setName(name);
        scenario.setContent(content);
        return scenarioService.addScenario(scenario);
    }

    @DeleteMapping("/{name}")
    public boolean delete(@PathVariable String name){
        return scenarioService.deleteByName(name);
    }

    @PutMapping
    public void change(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name");
        JSONArray content = jsonObject.getJSONArray("content");
        Scenario scenario = new Scenario();
        scenario.setName(name);
        scenario.setContent(content);
        scenarioService.changeScenario(scenario);
    }

    @GetMapping("/{name}")
    public Scenario findOneScenario(@PathVariable String name){
        return scenarioService.findByName(name);
    }

    @GetMapping
    public List<Scenario> findAll(){
        return scenarioService.findAll();
    }

}
