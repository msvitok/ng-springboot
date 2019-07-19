package sk.msvitok.app.external.jsonvat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Rate {

    private String name;

    private String code;

    @JsonProperty("country_code")
    private String countryCode;

    private List<Period> periods;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public List<Period> getPeriods() { return periods; }
    public void setPeriods(List<Period> periods) { this.periods = periods; }
}
