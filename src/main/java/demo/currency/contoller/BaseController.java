package demo.currency.contoller;

import demo.currency.exception.WebserviceException;
import demo.currency.facade.ClientRequestFacade;
import demo.currency.facade.ExchangeRateFacade;
import demo.currency.model.entity.ClientRequest;
import demo.currency.model.web.exception.ErrorWsDTO;
import demo.currency.service.ClientRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BaseController {


    private final ClientRequestFacade clientRequestFacade;
    private final ExchangeRateFacade exchangeRateFacade;
    private final ClientRequestService clientRequestService;

    public BaseController(ClientRequestFacade clientRequestFacade, ExchangeRateFacade exchangeRateFacade, ClientRequestService clientRequestService) {
        this.clientRequestFacade = clientRequestFacade;
        this.exchangeRateFacade = exchangeRateFacade;
        this.clientRequestService = clientRequestService;
    }


    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler({WebserviceException.class})
    public ErrorWsDTO handleWebserviceException(final WebserviceException ex) {
        System.err.println("Handling Exception for this request - " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        return handleErrorInternal(ex);
    }

    protected ErrorWsDTO handleErrorInternal(WebserviceException ex) {
        final ErrorWsDTO error = new ErrorWsDTO();
        error.setType(ex.getClass().getSimpleName().replace("Exception", "Error"));
        error.setMessage(ex.getMessage());
        error.setReason(ex.getReason());
        return error;
    }

    protected void validateRequestId(String requestId) {
        Optional<ClientRequest> clientRequestById = clientRequestService.findClientRequestById(requestId);

        if (clientRequestById.isPresent()) {
            throw new WebserviceException("Request with " + requestId + " already exist!", "Duplicate id");
        }
    }

    public ClientRequestFacade getClientRequestFacade() {
        return clientRequestFacade;
    }

    public ExchangeRateFacade getExchangeRateFacade() {
        return exchangeRateFacade;
    }
}
