package cn.mqcenter.thrift;

import cn.mqcenter.rabbit.SendMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MQController {
    @Autowired
    private SendMQ sender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendMQ(@RequestParam("uid") String uid, @RequestParam("msgid") String msgId, @RequestParam("os") String os, @RequestParam("env") String env, @RequestParam("msgtype") String msgType){
        System.out.println("准备发送消息。。。uid:" + uid + ", msgid:" + msgId + ", type:" + os + ", env:" + env + ", msgtype:" + msgType);
        Map<String, String> params = new HashMap();
        params.put("uid", uid);
        params.put("msgid", msgId);
        params.put("type", os);
        params.put("env", env);
        params.put("msgtype", msgType);

//        SendMQ sender = new SendMQ();
        sender.send(params);

        return "success";
    }
}
