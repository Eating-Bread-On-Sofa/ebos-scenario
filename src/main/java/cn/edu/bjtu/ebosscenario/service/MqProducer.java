package cn.edu.bjtu.ebosscenario.service;

public interface MqProducer {
    void publish(String topic, String message);
}
