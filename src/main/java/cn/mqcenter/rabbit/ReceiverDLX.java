package cn.mqcenter.rabbit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "direct.dlx")
public class ReceiverDLX {
    private static Logger logger = LogManager.getLogger(ReceiverDLX.class);
    @RabbitHandler
    public void process(Map<String, String> queue) throws Exception {
        logger.info("receiverDLX-xxxxxxxxxx:" + queue);
    }
}
