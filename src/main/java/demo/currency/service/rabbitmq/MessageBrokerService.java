package demo.currency.service.rabbitmq;

import demo.currency.model.service.ClientRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface MessageBrokerService {

    void sendMessage(ClientRequestDTO clientRequestDTO);

}
