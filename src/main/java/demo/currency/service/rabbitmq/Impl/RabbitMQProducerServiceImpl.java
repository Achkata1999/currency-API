package demo.currency.service.rabbitmq.Impl;

import demo.currency.model.service.ClientRequestDTO;
import demo.currency.service.rabbitmq.MessageBrokerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerServiceImpl implements MessageBrokerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key.name}")
    private String routingKey;

    public RabbitMQProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(ClientRequestDTO clientRequestDTO) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, clientRequestDTO);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
