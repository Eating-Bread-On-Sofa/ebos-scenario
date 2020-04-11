package cn.edu.bjtu.ebosscenario.service.impl;

import cn.edu.bjtu.ebosscenario.domain.Log;
import cn.edu.bjtu.ebosscenario.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private static String serviceName = "场景";
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void debug(String massage){
        Log log = new Log();
        log.setData(new Date());
        log.setCategory("debug");
        log.setMassage(massage);
        log.setSource(getTop());
        mongoTemplate.save(log);
    }
    @Override
    public void info(String massage){
        Log log = new Log();
        log.setData(new Date());
        log.setCategory("info");
        log.setMassage(massage);
        log.setSource(serviceName);
        mongoTemplate.save(log);
    }
    @Override
    public void warn(String massage){
        Log log = new Log();
        log.setData(new Date());
        log.setCategory("warn");
        log.setMassage(massage);
        log.setSource(getTop());
        mongoTemplate.save(log);
    }
    @Override
    public void error(String massage){
        Log log = new Log();
        log.setData(new Date());
        log.setCategory("debug");
        log.setMassage(massage);
        log.setSource(getTop());
        mongoTemplate.save(log);
    }
    @Override
    public String findAll(){
        List<Log> list = mongoTemplate.findAll(Log.class);
        return list2str(list);
    }
    @Override
    public String findLogByCategory(String category){
        Query query = Query.query(Criteria.where("category").is(category));
        List<Log> list = mongoTemplate.find(query , Log.class);
        return list2str(list);
    }
    @Override
    public String getTop() {
        // 获取堆栈信息
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        // 最原始被调用的堆栈信息
        StackTraceElement caller = null;
        // 日志类名称
        String logClassName = LogServiceImpl.class.getName();
        // 循环遍历到日志类标识
        boolean isEachLogClass = false;
        // 遍历堆栈信息，获取出最原始被调用的方法信息
        for (StackTraceElement s : callStack) {
            // 遍历到日志类
            if (logClassName.equals(s.getClassName())) {
                isEachLogClass = true;
            }
            // 下一个非日志类的堆栈，就是最原始被调用的方法
            if (isEachLogClass) {
                if(!logClassName.equals(s.getClassName())) {
                    caller = s;
                    break;
                }
            }
        }
        if(caller != null) {
            return caller.toString();
        }else{
            return  "";
        }
    }

    private String list2str(List<Log> list){
        //使用StringBuilder提高性能
        StringBuilder stringBuilder = new StringBuilder();
        for(Log log:list){
            stringBuilder.append(log.getData()).append("   [").append(log.getCategory()).append("]   ").append(log.getSource()).append(" - ").append(log.getMassage()).append("\n");
        }
        if(stringBuilder.length()==0){return "日志为空";}else {
            return stringBuilder.substring(0,stringBuilder.length()-1);}
    }
}
