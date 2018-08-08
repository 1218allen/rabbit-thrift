package cn.mqcenter.thrift;

import cn.mqcenter.Message;
import cn.mqcenter.rabbit.SendMQ;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageImpl implements Message.Iface {
    @Override
    public String sendmessage(Map<String, String> params) throws TException {
        SendMQ sendMQ = new SendMQ();
        sendMQ.send(params);

        return "send success";
    }
}
