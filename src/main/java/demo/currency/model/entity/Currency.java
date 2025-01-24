package demo.currency.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Currency implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String isoCode;

    private String name;

    @OneToMany(mappedBy = "baseCurrency", cascade = CascadeType.ALL)
    private Set<ExchangeRate> baseExchangeRates;

    @OneToMany(mappedBy = "targetCurrency", cascade = CascadeType.ALL)
    private Set<ExchangeRate> targetExchangeRates;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ExchangeRate> getBaseExchangeRates() {
        return baseExchangeRates;
    }

    public void setBaseExchangeRates(Set<ExchangeRate> baseExchangeRates) {
        this.baseExchangeRates = baseExchangeRates;
    }

    public Set<ExchangeRate> getTargetExchangeRates() {
        return targetExchangeRates;
    }

    public void setTargetExchangeRates(Set<ExchangeRate> targetExchangeRates) {
        this.targetExchangeRates = targetExchangeRates;
    }
}
