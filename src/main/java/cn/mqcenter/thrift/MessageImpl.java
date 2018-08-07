package cn.mqcenter.thrift;

import cn.mqcenter.Message;
import cn.mqcenter.rabbit.SendMQ;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageImpl implements Message.Iface {
    @Override
    public String sendmessage(Map<String, String> params) throws TException {
        SendMQ sendMQ = new SendMQ();
        sendMQ.send(params);

        return "send success";
    }
}
