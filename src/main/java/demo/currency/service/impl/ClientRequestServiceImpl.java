package demo.currency.service.impl;

import demo.currency.model.entity.ClientRequest;
import demo.currency.repository.ClientRequestRepository;
import demo.currency.repository.CustomerRepository;
import demo.currency.service.ClientRequestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ClientRequestServiceImpl implements ClientRequestService {

    private final CustomerRepository customerRepository;

    private final ClientRequestRepository clientRequestRepository;

    public ClientRequestServiceImpl(CustomerRepository customerRepository, ClientRequestRepository clientRequestRepository) {
        this.customerRepository = customerRepository;
        this.clientRequestRepository = clientRequestRepository;
    }

    @Override
    public ClientRequest saveClientRequest(String requestId, String serviceName, Instant requestTime, String clientId) {
        ClientRequest clientRequest = new ClientRequest();

        customerRepository.findById(clientId).ifPresent(customer -> {
            clientRequest.setRequestId(requestId);
            clientRequest.setServiceName(serviceName);
            clientRequest.setRequestTime(requestTime);
            clientRequest.setClientId(customer);

        });

        return clientRequestRepository.save(clientRequest);
    }

    @Override
    public Optional<ClientRequest> findClientRequestById(String requestId) {
       return clientRequestRepository.findById(requestId);
    }


}
