package demo.currency.service.impl;

import demo.currency.model.entity.ExchangeRate;
import demo.currency.repository.ExchangeRateRepository;
import demo.currency.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private static final String CACHE_NAME = "currencyCache";

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#baseCurrency")
    public List<ExchangeRate> findLatestExchangeRates(String baseCurrency) {
        List<ExchangeRate> latestExchangeRates = exchangeRateRepository.findLatestExchangeRates(baseCurrency);

        return latestExchangeRates;
    }

    @Override
    public List<ExchangeRate> findHistoryExchangeRates(String baseCurrency, Instant historyTime) {
        List<ExchangeRate> historyExchangeRates = exchangeRateRepository.findHistoryExchangeRates(baseCurrency, historyTime);

        return historyExchangeRates;
    }
}
