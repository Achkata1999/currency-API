package demo.currency.model.web.json;

public class CustomerRequestByPeriodWsDTO extends CustomerRequestWsDTO{

    private int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
