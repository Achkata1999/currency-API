package demo.currency.contoller;

import demo.currency.facade.ClientRequestFacade;
import demo.currency.facade.ExchangeRateFacade;
import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.web.response.ExchangeRateResponseWsDTO;
import demo.currency.model.web.xml.Command;
import demo.currency.model.web.xml.HelperClassXML;
import demo.currency.service.ClientRequestService;
import demo.currency.util.ControllerHelper;
import demo.currency.util.ExchangeRateResponseHelper;
import demo.currency.util.XMLHelper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/xml_api")
public class CurrencyRateXMLController extends BaseController {
    private static final String SERVICE_API_NAME = "EXT_SERVICE_1";

    public CurrencyRateXMLController(ClientRequestFacade clientRequestFacade, ExchangeRateFacade exchangeRateFacade, ClientRequestService clientRequestService) {
        super(clientRequestFacade, exchangeRateFacade, clientRequestService);
    }

    @PostMapping(value = "/command", produces = {MediaType.APPLICATION_XML_VALUE})
    public List<ExchangeRateResponseWsDTO> getCurrencyRate(@RequestBody Command command) {
        Instant timestamp = Instant.now();
        HelperClassXML requestData = XMLHelper.mapRequestData(command, timestamp);

        validateRequestId(requestData.getRequestId());

        getClientRequestFacade()
                .saveClientRequest(requestData.getRequestId(), requestData.getClientId(), SERVICE_API_NAME, requestData.getTimestamp());

        if (command.getGet() != null) {
            List<ExchangeRate> latestExchangeRates = getExchangeRateFacade()
                    .getCurrencyExchangeRate(requestData.getCurrency());

            return ExchangeRateResponseHelper.buildListExchangeRateResponseWsDTO(latestExchangeRates);
        }
        List<ExchangeRate> historyExchangeRates = getExchangeRateFacade()
                .getCurrencyExchangeRateByPeriod(requestData.getCurrency(), ControllerHelper.calculateHistoryPeriod(requestData.getPeriod()));

        return ExchangeRateResponseHelper.buildListExchangeRateResponseWsDTO(historyExchangeRates);
    }
}
