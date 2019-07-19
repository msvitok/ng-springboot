package sk.msvitok.app.external.jsonvat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class Period {

    public static final String STANDARD_RATE_KEY = "standard";

    @JsonProperty("effective_from")
    private Date effectiveFrom;

    private Map<String, Double> rates;

    public Date getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(Date effectiveFrom) { this.effectiveFrom = effectiveFrom; }

    public Map<String, Double> getRates() { return rates; }
    public void setRates(Map<String, Double> rates) { this.rates = rates; }
}
