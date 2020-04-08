package cn.edu.bjtu.ebosscenario.domain;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Scenario {
    @Id
    private String name;
    private JSONArray content;

    public Scenario() {
    }

    public Scenario(String name, JSONArray content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getContent() {
        return content;
    }

    public void setContent(JSONArray content) {
        this.content = content;
    }
}
