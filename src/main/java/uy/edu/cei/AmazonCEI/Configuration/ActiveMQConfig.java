package uy.edu.cei.AmazonCEI.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class ActiveMQConfig {
    public static final String SHOPPING_CART_QUEUE = "shopping-cart-queue";
    public static final String SHOPPING_CART_QUEUE_FOR_CHECKOUT = "shopping-cart-queue-for-checkout";
    public static final String CHECKOUT_QUEUE_FOR_NOTIFICATION= "checkout-queue-for-notification";
    public static final String CHECKOUT_QUEUE_FOR_UPDATE= "checkout-queue-for-update";
    public static final String CHECKOUT_QUEUE_FOR_CLOSE= "checkout-queue-for-close";

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
