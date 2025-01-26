package demo.currency.facade;

import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.web.xml.Command;
import demo.currency.model.web.xml.HelperClassXML;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ExchangeRateFacade {

    List<ExchangeRate> getCurrencyExchangeRate(String baseCurrency);

    List<ExchangeRate> getCurrencyExchangeRateByPeriod(String baseCurrency, Instant historyTime);

    List<ExchangeRate> getCurrencyExchangeRateXML(Command command, HelperClassXML requestData);


}
