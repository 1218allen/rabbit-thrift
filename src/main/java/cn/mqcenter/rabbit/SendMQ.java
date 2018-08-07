package cn.mqcenter.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendMQ {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private FanoutExchange exchange;

    public SendMQ() {
        rabbitTemplate = new RabbitTemplate(connectionFactory());
        queue = new Queue("fanout.messages");
        exchange = new FanoutExchange("fanoutExchange");
    }

    public void send(Map<String, String> params) {
        System.out.println("exchange:" + exchange.getName());
        System.out.println("queue:" + queue.getName());
        this.rabbitTemplate.convertAndSend(exchange.getName(), queue.getName(), params);
    }

    private ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("192.168.107.200:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        return connectionFactory;
    }
}
