package demo.currency.converter;

import demo.currency.model.entity.ClientRequest;
import demo.currency.model.service.ClientRequestDTO;

public class ClientRequestDTOConverter {

    public static ClientRequestDTO toClientRequestDTO(ClientRequest clientRequest) {
        if (clientRequest == null) {
            return null;
        }

        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();

        clientRequestDTO.setRequestId(clientRequest.getRequestId());
        clientRequestDTO.setClientId(clientRequest.getClientId().getId());
        clientRequestDTO.setServiceName(clientRequest.getServiceName());
        clientRequestDTO.setRequestTime(clientRequest.getRequestTime());

        return clientRequestDTO;
    }
}
