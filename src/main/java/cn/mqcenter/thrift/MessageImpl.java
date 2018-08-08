package cn.mqcenter.thrift;

import cn.mqcenter.Message;
import cn.mqcenter.rabbit.SendMQ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageImpl implements Message.Iface {
    private static Logger logger = LogManager.getLogger(MessageImpl.class);
    @Override
    public String sendmessage(Map<String, String> params) throws TException {
        logger.info("send mq params:" + params);

        SendMQ sendMQ = new SendMQ();
        sendMQ.send(params);

        return "send success";
    }
}
