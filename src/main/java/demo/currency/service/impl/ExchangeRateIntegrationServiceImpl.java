package demo.currency.service.impl;

import demo.currency.model.service.CurrencyDTO;
import demo.currency.model.service.ExchangeRateDTO;
import demo.currency.service.ExchangeRateIntegrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateIntegrationServiceImpl implements ExchangeRateIntegrationService {

    public static final String QUERY_SEPARATOR = "?"; // For "?"
    public static final String PARAMETER_SEPARATOR = "&";
    public static final String BASE_PARAM_NAME = "base=";
    public static final String APIKEY_PARAM_NAME = "apikey=";
    @Value("${fixer.api.url}")
    private String apiUrl;

    @Value("${fixer.api.url.symbols}")
    private String apiSymbols;

    @Value("${fixer.api.url.latest}")
    private String apiLatest;

    @Value("${fixer.api.key}")
    private String apiKey;

    @Override
    public List<CurrencyDTO> fetchAllCurrencies() {
        List<CurrencyDTO> currencyDTOList = new ArrayList<>();

        String url = apiUrl + apiSymbols + QUERY_SEPARATOR + APIKEY_PARAM_NAME + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> body = response.getBody();
        Map<String, String> symbols = (Map<String, String>) body.get("symbols");

        symbols.forEach((key, value) -> {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setIsoCode(key);
            currencyDTO.setName(value);
            currencyDTOList.add(currencyDTO);
        });

        return currencyDTOList;
    }

    @Override
    public List<ExchangeRateDTO> fetchExchangeRates(String isoCode) {
        List<ExchangeRateDTO> exchangeRates = new ArrayList<>();

        String url = apiUrl + apiLatest + QUERY_SEPARATOR + BASE_PARAM_NAME + isoCode + PARAMETER_SEPARATOR + APIKEY_PARAM_NAME + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> body = response.getBody();
        String baseCurrency = (String) body.get("base");
        Map<String, Object> rates = (Map<String, Object>) body.get("rates");
        long timestampLong = ((Integer) body.get("timestamp")).longValue();
        Instant timestamp = Instant.ofEpochSecond(timestampLong);

        rates.forEach((targetCurrency, rate) -> {

            ExchangeRateDTO exchangeRateDTO = new ExchangeRateDTO();
            exchangeRateDTO.setBaseCurrency(baseCurrency);
            exchangeRateDTO.setTargetCurrency(targetCurrency);
            exchangeRateDTO.setRate(new BigDecimal(rate.toString()));
            exchangeRateDTO.setTimestamp(timestamp);
            exchangeRates.add(exchangeRateDTO);
        });

        return exchangeRates;
    }

}
