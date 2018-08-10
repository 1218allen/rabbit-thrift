package cn.mqcenter.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue dlxMessage() {
        return new Queue("direct.dlx");
    }

    @Bean
    public Queue queueMessage(){
        Map<String, Object> parmas = new HashMap<>();
        parmas.put("x-dead-letter-exchange", "dlxExchange");
        parmas.put("x-dead-letter-routing-key", "direct.dlx");

        return new Queue("fanout.messages", true, false, false, parmas);
    }

    @Bean
    public Queue queueMS(){
        return new Queue("fanout.ms");
    }

    @Bean
    public Queue topicQueueMessage() {
        return new Queue("topic.messages");
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange("dlxExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeMS(Queue queueMS, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMS).to(exchange);
    }

    @Bean
    Binding bindingTopicExchangeMessage(Queue topicQueueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueMessage).to(topicExchange).with("topic.messages");
    }

    @Bean
    Binding bindingDlxExchangMessage(Queue dlxMessage, DirectExchange dlxExchange) {
        return BindingBuilder.bind(dlxMessage).to(dlxExchange).with("direct.dlx");
    }
}
