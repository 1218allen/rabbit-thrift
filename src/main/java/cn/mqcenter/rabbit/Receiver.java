package cn.mqcenter.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    @RabbitHandler
    public void process(Map<String, String> queue) throws Exception {
        String url = "";
        LinkedMultiValueMap body = new LinkedMultiValueMap();

        for (Map.Entry<String, String> entry : queue.entrySet()) {
            System.out.println("fanoutMessageReceiver: key=" + entry.getKey() + ", value:" + entry.getValue());

            if (entry.getKey().equals("url")) {
                System.out.println("http:" + url);
                url = entry.getValue();
            } else {
                body.add(entry.getKey(), entry.getValue());
            }
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity entity = new HttpEntity(body, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
