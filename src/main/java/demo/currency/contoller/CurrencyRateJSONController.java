package demo.currency.contoller;

import demo.currency.facade.ClientRequestFacade;
import demo.currency.facade.ExchangeRateFacade;
import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.web.json.CustomerRequestByPeriodWsDTO;
import demo.currency.model.web.json.CustomerRequestWsDTO;
import demo.currency.model.web.response.ExchangeRateResponseWsDTO;
import demo.currency.service.ClientRequestService;
import demo.currency.util.ControllerHelper;
import demo.currency.util.ExchangeRateResponseHelper;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/json_api")
public class CurrencyRateJSONController extends BaseController {

    private static final String SERVICE_API_NAME = "EXT_SERVICE_2";

    public CurrencyRateJSONController(ClientRequestFacade clientRequestFacade, ExchangeRateFacade exchangeRateFacade, ClientRequestService clientRequestService) {
        super(clientRequestFacade, exchangeRateFacade, clientRequestService);
    }

    @PostMapping("/current")
    public ExchangeRateResponseWsDTO getLastCurrencyRate(@RequestBody CustomerRequestWsDTO customerRequestWsDTO) {
        validateRequestId(customerRequestWsDTO.getRequestId());

        getClientRequestFacade()
                .saveClientRequest(customerRequestWsDTO.getRequestId(), customerRequestWsDTO.getClient(), SERVICE_API_NAME, Instant.ofEpochMilli(customerRequestWsDTO.getTimestamp()));

        List<ExchangeRate> currencyExchangeRate = getExchangeRateFacade()
                .getCurrencyExchangeRate(customerRequestWsDTO.getCurrency());

        return ExchangeRateResponseHelper.buildExchangeRateResponseWsDTO(currencyExchangeRate);
    }

    @PostMapping("/history")
    public List<ExchangeRateResponseWsDTO> getCurrencyRateByPeriod(@RequestBody CustomerRequestByPeriodWsDTO customerRequestByPeriodWsDTO) {
        validateRequestId(customerRequestByPeriodWsDTO.getRequestId());

        getClientRequestFacade()
                .saveClientRequest(customerRequestByPeriodWsDTO.getRequestId(), customerRequestByPeriodWsDTO.getClient(), SERVICE_API_NAME, Instant.ofEpochMilli(customerRequestByPeriodWsDTO.getTimestamp()));

        List<ExchangeRate> historyExchangeRates = getExchangeRateFacade()
                .getCurrencyExchangeRateByPeriod(customerRequestByPeriodWsDTO.getCurrency(), ControllerHelper.calculateHistoryPeriod(customerRequestByPeriodWsDTO.getPeriod()));

        return ExchangeRateResponseHelper.buildListExchangeRateResponseWsDTO(historyExchangeRates);
    }

}
