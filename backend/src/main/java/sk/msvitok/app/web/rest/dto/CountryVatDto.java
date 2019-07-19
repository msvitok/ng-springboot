package sk.msvitok.app.web.rest.dto;

import java.util.Date;

public class CountryVatDto {

    private String name;

    private String code;

    private String countryCode;

    private double standardVat;

    private Date effectiveFrom;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public double getStandardVat() { return standardVat; }
    public void setStandardVat(double standardVat) { this.standardVat = standardVat; }

    public Date getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(Date effectiveFrom) { this.effectiveFrom = effectiveFrom; }
}
