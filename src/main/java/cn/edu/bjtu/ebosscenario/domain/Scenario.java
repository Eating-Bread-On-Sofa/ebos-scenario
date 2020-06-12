package cn.edu.bjtu.ebosscenario.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document
public class Scenario {
    @Id
    private String name;
    private Gateway[] content;
    private Date created;
    private Set<String> rules;

    public Scenario() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gateway[] getContent() {
        return content;
    }

    public void setContent(Gateway[] content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<String> getRules() {
        return rules;
    }

    public void setRules(Set<String> rules) {
        this.rules = rules;
    }
}
