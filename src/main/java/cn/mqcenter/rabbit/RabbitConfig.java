package cn.mqcenter.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queueMessage(){
        return new Queue("fanout.messages");
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }
}
