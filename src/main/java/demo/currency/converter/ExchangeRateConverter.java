package demo.currency.converter;

import demo.currency.model.entity.Currency;
import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.service.ExchangeRateDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateConverter {

    public static ExchangeRate toEntity(ExchangeRateDTO dto, Currency baseCurrency, Currency targetCurrency) {
        if (dto == null || baseCurrency == null || targetCurrency == null) {
            return null;
        }

        ExchangeRate exchangeRate = new ExchangeRate();

        exchangeRate.setBaseCurrency(baseCurrency);
        exchangeRate.setTargetCurrency(targetCurrency);
        exchangeRate.setRate(dto.getRate());
        exchangeRate.setUpdateDateTime(dto.getTimestamp());

        return exchangeRate;
    }

    public static List<ExchangeRate> toEntityList(List<ExchangeRateDTO> dtoList, Currency baseCurrency, Currency targetCurrency) {
        if (dtoList == null || dtoList.isEmpty() || baseCurrency == null || targetCurrency == null) {
            return new ArrayList<>();
        }

        return dtoList.stream()
                .map(dto -> toEntity(dto, baseCurrency,targetCurrency))
                .collect(Collectors.toList());
    }
}
