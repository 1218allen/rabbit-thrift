package cn.mqcenter.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendMQ {
    private AmqpTemplate rabbitTemplate;
    private Queue queue;
    private FanoutExchange exchange;
    private PropertiesConfig config;

    public SendMQ() {
        config = new PropertiesConfig();
    }

    public void send(Map<String, String> params) {
        queue = new Queue(config.getQueue());
        exchange = new FanoutExchange(config.getExchange());
        System.out.println("exchange:" + exchange.getName());
        System.out.println("queue:" + queue.getName());
        rabbitTemplate = new RabbitTemplate(connectionFactory());
        this.rabbitTemplate.convertAndSend(exchange.getName(), queue.getName(), params);
    }

    private ConnectionFactory connectionFactory() {
        System.out.println("host:" + config.getHost() + ", port:" + config.getPort() + ", user:" + config.getUsername() + ", pwd:" + config.getPassword() + ", vhost:" + config.getVirtualHost());
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(config.getHost() + ":" + config.getPort());
        connectionFactory.setUsername(config.getUsername());
        connectionFactory.setPassword(config.getPassword());
        connectionFactory.setVirtualHost(config.getVirtualHost());

        return connectionFactory;
    }
}
