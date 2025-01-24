package demo.currency.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.Instant;

@Entity
public class ClientRequest implements Serializable {

    @Id
    private String requestId;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Customer clientId;

    private String serviceName;

    private Instant requestTime;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Customer getClientId() {
        return clientId;
    }

    public void setClientId(Customer clientId) {
        this.clientId = clientId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Instant requestTime) {
        this.requestTime = requestTime;
    }
}
