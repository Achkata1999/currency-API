package demo.currency.service;

import demo.currency.model.entity.ExchangeRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ExchangeRateService {

    List<ExchangeRate> findLatestExchangeRates(String baseCurrency);

    List<ExchangeRate> findHistoryExchangeRates(String baseCurrency, Instant historyTime);

}
