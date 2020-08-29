package cn.edu.bjtu.ebosscenario.service;

import cn.edu.bjtu.ebosscenario.controller.ScenarioController;
import cn.edu.bjtu.ebosscenario.domain.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Order(2)
public class InitRawSubscribe implements ApplicationRunner {

    @Autowired
    SubscribeService subscribeService;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 50,3, TimeUnit.SECONDS,new SynchronousQueue<>());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Subscribe> subscribes = subscribeService.findByServiceName();

        for (Subscribe subscribe : subscribes){
            RawSubscribe rawSubscribe = new RawSubscribe(subscribe.getSubTopic());
            ScenarioController.status.add(rawSubscribe);
            threadPoolExecutor.execute(rawSubscribe);
        }
    }
}
