package cn.mqcenter.rabbit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
//@PropertySource("classpath:config/mq.properties")
public class PropertiesConfig {
    public static String host;
    public static String port;
    public static String username;
    public static String password;
    public static String virtualHost;
    public static String queue;
    public static String exchange;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        PropertiesConfig.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        PropertiesConfig.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PropertiesConfig.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        PropertiesConfig.virtualHost = virtualHost;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
