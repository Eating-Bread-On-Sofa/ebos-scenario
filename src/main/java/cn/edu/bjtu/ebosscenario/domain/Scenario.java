package cn.edu.bjtu.ebosscenario.domain;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Scenario {
    @Id
    private String name;
    private JSONArray content;
    private Date created;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
