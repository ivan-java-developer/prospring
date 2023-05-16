package com.prospring.ch12;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Application {
    private final static String queueName = "forecasts";
    private final static String exchangeName = "weather";

    @Bean
    public Queue forecasts() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange weather() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Binding dataBinding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(queueName);
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("127.0.0.1");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(5);
        return factory;
    }

    @Bean
    public RabbitTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setReplyTimeout(2000);
        rabbitTemplate.setRoutingKey(queueName);
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("FL");
        rabbitTemplate.convertAndSend("MA");
        rabbitTemplate.convertAndSend("CA");

        System.in.read();
        ctx.close();
    }
}
