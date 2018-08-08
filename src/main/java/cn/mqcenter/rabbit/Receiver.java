package cn.mqcenter.rabbit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.messages")
public class Receiver {
    private static Logger logger = LogManager.getLogger(Receiver.class);

    @Value("${spring.rabbitmq.push-url}")
    private String url;

    @RabbitHandler
    public void process(Map<String, String> queue) throws Exception {
        logger.info("fanoutMessageReceiver params:" + queue);

        LinkedMultiValueMap body = new LinkedMultiValueMap();

        for (Map.Entry<String, String> entry : queue.entrySet()) {
            body.add(entry.getKey(), entry.getValue());
        }

        logger.info("fanoutMessageReceiver: pushurl=" + url);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity entity = new HttpEntity(body, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
