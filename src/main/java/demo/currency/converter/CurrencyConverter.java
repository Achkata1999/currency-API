package demo.currency.converter;

import demo.currency.model.entity.Currency;
import demo.currency.model.service.CurrencyDTO;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {
    public static Currency toEntity(CurrencyDTO dto) {
        if (dto == null) {
            return null;
        }

        Currency currency = new Currency();
        currency.setIsoCode(dto.getIsoCode());
        currency.setName(dto.getName());
        return currency;
    }

    public static List<Currency> toEntityList(List<CurrencyDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return new ArrayList<>();
        }

        return dtoList.stream()
                .map(CurrencyConverter::toEntity)
                .toList();
    }
}
