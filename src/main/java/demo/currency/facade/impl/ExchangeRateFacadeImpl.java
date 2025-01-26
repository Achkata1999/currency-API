package demo.currency.facade.impl;

import demo.currency.facade.ExchangeRateFacade;
import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.web.xml.Command;
import demo.currency.model.web.xml.HelperClassXML;
import demo.currency.service.ExchangeRateService;
import demo.currency.util.InstantHelper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ExchangeRateFacadeImpl implements ExchangeRateFacade {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateFacadeImpl(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public List<ExchangeRate> getCurrencyExchangeRate(String baseCurrency) {
        return exchangeRateService.findLatestExchangeRates(baseCurrency);
    }

    @Override
    public List<ExchangeRate> getCurrencyExchangeRateByPeriod(String baseCurrency, Instant historyTime) {
        return exchangeRateService.findHistoryExchangeRates(baseCurrency, historyTime);
    }

    @Override
    public List<ExchangeRate> getCurrencyExchangeRateXML(Command command, HelperClassXML requestData) {
        if (command.getGet() != null) {
            return getCurrencyExchangeRate(requestData.getCurrency());
        }
        return getCurrencyExchangeRateByPeriod(requestData.getCurrency(), InstantHelper.calculateHistoryPeriod(requestData.getPeriod()));
    }
}
