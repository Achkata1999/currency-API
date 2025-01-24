package demo.currency.repository;

import demo.currency.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.isoCode = :baseCurrency AND e.updateDateTime = (SELECT MAX(e2.updateDateTime) FROM ExchangeRate e2 WHERE e2.baseCurrency.isoCode = :baseCurrency)")
    List<ExchangeRate> findLatestExchangeRates(@Param("baseCurrency") String baseCurrency);

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.isoCode = :baseCurrency AND e.updateDateTime > :historyTime ")
    List<ExchangeRate> findHistoryExchangeRates(@Param("baseCurrency") String baseCurrency, @Param("historyTime") Instant historyTime);

}
