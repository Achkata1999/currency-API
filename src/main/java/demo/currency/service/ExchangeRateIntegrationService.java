package demo.currency.service;

import demo.currency.model.service.CurrencyDTO;
import demo.currency.model.service.ExchangeRateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeRateIntegrationService {

    List<CurrencyDTO> fetchAllCurrencies();

    List<ExchangeRateDTO> fetchExchangeRates(String isoCode);
}