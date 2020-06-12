package cn.edu.bjtu.ebosscenario.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class ScenarioMessage {
    private String type;
    private String message;
    private String source;
    private List<Scenario> scenarioList;
    private Date created;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Scenario> getScenarioList() {
        return scenarioList;
    }

    public void setScenarioList(List<Scenario> scenarioList) {
        this.scenarioList = scenarioList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
