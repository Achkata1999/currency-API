package demo.currency.util;

import demo.currency.model.entity.ExchangeRate;
import demo.currency.model.web.response.ExchangeRateResponseWsDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ExchangeRateResponseHelper {

    public static ExchangeRateResponseWsDTO buildExchangeRateResponseWsDTO(List<ExchangeRate> exchangeRates) {
        ExchangeRateResponseWsDTO exchangeRateResponse = new ExchangeRateResponseWsDTO();
        Map<String, BigDecimal> rates = new TreeMap<>();

        exchangeRates.stream()
                .findAny()
                .ifPresent(exchangeRate -> {
                    exchangeRateResponse.setBase(exchangeRate.getBaseCurrency().getIsoCode());
                    exchangeRateResponse.setTimestamp(exchangeRate.getUpdateDateTime().toEpochMilli());
                    exchangeRateResponse.setDate(convertSecondsToDate(exchangeRate.getUpdateDateTime()));

                });

        exchangeRates.forEach(exchangeRate -> rates.put(exchangeRate.getTargetCurrency().getIsoCode(), exchangeRate.getRate()));

        exchangeRateResponse.setRates(rates);

        return exchangeRateResponse;
    }

    public static List<ExchangeRateResponseWsDTO> buildListExchangeRateResponseWsDTO(List<ExchangeRate> exchangeRates) {
        List<ExchangeRateResponseWsDTO> exchangeRateResponses = new ArrayList<>();

        exchangeRates.stream()
                .collect(Collectors.groupingBy(ExchangeRate::getUpdateDateTime))
                .forEach((instant, exchangeRate1) -> exchangeRateResponses.add(buildExchangeRateResponseWsDTO(exchangeRate1)));

        exchangeRateResponses.sort(Comparator.comparingLong(ExchangeRateResponseWsDTO::getTimestamp).reversed());

        return exchangeRateResponses;
    }

    private static String convertSecondsToDate(Instant timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ZonedDateTime dateTime = timestamp.atZone(ZoneOffset.UTC);

        return dateTime.format(formatter) + " UTC";
    }


}
