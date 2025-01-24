package demo.currency.facade.impl;

import demo.currency.converter.ClientRequestDTOConverter;
import demo.currency.facade.ClientRequestFacade;
import demo.currency.model.entity.ClientRequest;
import demo.currency.service.ClientRequestService;
import demo.currency.service.CustomerService;
import demo.currency.service.rabbitmq.MessageBrokerService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClientRequestFacadeImpl implements ClientRequestFacade {

    private final CustomerService customerService;
    private final ClientRequestService clientRequestService;
    private final MessageBrokerService messageBrokerService;

    public ClientRequestFacadeImpl(CustomerService customerService, ClientRequestService clientRequestService, MessageBrokerService messageBrokerService) {
        this.customerService = customerService;
        this.clientRequestService = clientRequestService;
        this.messageBrokerService = messageBrokerService;
    }

    public ClientRequest saveClientRequest(String requestId, String clientId, String serviceName, Instant timestamp) {
        customerService.saveCustomer(clientId);
        ClientRequest clientRequest = clientRequestService.saveClientRequest(requestId, serviceName, timestamp, clientId);

        if (clientRequest != null) {
            messageBrokerService.sendMessage(ClientRequestDTOConverter.toClientRequestDTO(clientRequest));
        }
        return clientRequest;
    }
}
