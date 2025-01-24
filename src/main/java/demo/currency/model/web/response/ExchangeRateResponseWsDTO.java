package demo.currency.model.web.response;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateResponseWsDTO {
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
    private long timestamp;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

