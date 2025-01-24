package demo.currency.task;

import demo.currency.converter.CurrencyConverter;
import demo.currency.converter.ExchangeRateConverter;
import demo.currency.model.entity.Currency;
import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.service.CurrencyDTO;
import demo.currency.model.service.ExchangeRateDTO;
import demo.currency.repository.CurrencyRepository;
import demo.currency.repository.ExchangeRateRepository;
import demo.currency.service.ExchangeRateIntegrationService;
import demo.currency.service.cache.CacheService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledCurrencyRateTask {

    private final ExchangeRateIntegrationService exchangeRateIntegrationService;
    private final CacheService cacheService;

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyRepository currencyRepository;

    public ScheduledCurrencyRateTask(ExchangeRateIntegrationService exchangeRateIntegrationService, ExchangeRateRepository exchangeRateRepository, CurrencyRepository currencyRepository, CacheService cacheService) {
        this.exchangeRateIntegrationService = exchangeRateIntegrationService;
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyRepository = currencyRepository;
        this.cacheService = cacheService;
    }

    @Scheduled(fixedRateString = "${update.interval}")
    public void fetchAndSaveCurrencyRates() {
        List<CurrencyDTO> currencyDTOList = exchangeRateIntegrationService.fetchAllCurrencies();
        List<Currency> entityList = CurrencyConverter.toEntityList(currencyDTOList);
        currencyRepository.saveAll(entityList);

        currencyDTOList.forEach(currencyDTO -> {
            try {
                System.out.println("Exchange currency");
                List<ExchangeRateDTO> exchangeRateDTOS = exchangeRateIntegrationService.fetchExchangeRates(currencyDTO.getIsoCode());
                exchangeRateDTOS.forEach(exchangeRateDTO -> {
                    Currency baseCurrency = currencyRepository.findByIsoCode(exchangeRateDTO.getBaseCurrency());
                    Currency targetCurrency = currencyRepository.findByIsoCode(exchangeRateDTO.getTargetCurrency());

                    ExchangeRate entity = ExchangeRateConverter.toEntity(exchangeRateDTO, baseCurrency, targetCurrency);
                    exchangeRateRepository.save(entity);
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        cacheService.clearAllCaches();
    }
}
