package cn.mqcenter.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queueMessage(){
        return new Queue("fanout.messages");
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
}
