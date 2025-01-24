package demo.currency.service;

import demo.currency.model.entity.ClientRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public interface ClientRequestService {

    ClientRequest saveClientRequest(String requestId, String serviceName, Instant timestamp, String clientId);

    Optional<ClientRequest> findClientRequestById(String requestId);
}
