package demo.currency.facade;

import demo.currency.model.entity.ClientRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public interface ClientRequestFacade {

    ClientRequest saveClientRequest(String requestId, String clientId, String serviceName, Instant timestamp);
}
