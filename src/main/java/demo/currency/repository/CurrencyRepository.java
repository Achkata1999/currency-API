package demo.currency.repository;

import demo.currency.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Currency findByIsoCode(String isoCode);
}
