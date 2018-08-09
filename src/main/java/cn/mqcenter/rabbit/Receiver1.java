package cn.mqcenter.rabbit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.ms")
public class Receiver1 {
    private static Logger logger = LogManager.getLogger(Receiver1.class);
    @RabbitHandler
    public void process(Map<String, String> queue) throws Exception {
        logger.info("receiver111111111:" + queue);
    }
}
