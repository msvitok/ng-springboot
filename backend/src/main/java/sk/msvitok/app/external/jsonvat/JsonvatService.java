package sk.msvitok.app.external.jsonvat;

import sk.msvitok.app.backend.entity.CountryVat;
import sk.msvitok.app.tools.ResponseFetcher;
import sk.msvitok.app.utils.Jsons;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static sk.msvitok.app.utils.Lists.lastOf;

@Service
public class JsonvatService {

    private static final Log log = LogFactory.getLog(JsonvatService.class);

    private static final String URL = "http://jsonvat.com/";

    public List<CountryVat> getCountryVats() {
        Jsonvat jsonvat = tryGetJsonvat();
        if (jsonvat == null) {
            return Collections.emptyList();
        }
        List<Rate> rates = jsonvat.getRates();
        return toCountryVats(rates);
    }

    /* --------------------------- helper-methods --------------------------- */

    private @Nullable Jsonvat tryGetJsonvat() {
        String json = ResponseFetcher.tryFetch(URL, null);
        if (json == null) {
            log.info("Unable to fetch json");
            return null;
        }
        Jsonvat jsonvat = Jsons.tryReadObject(json, Jsonvat.class, null);
        if (jsonvat == null) {
            log.info("Unable to parse Jsonvat");
            return null;
        }
        return jsonvat;
    }

    private List<CountryVat> toCountryVats(List<Rate> rates) {
        return rates.stream()
                .map(JsonvatService::toCountryVat)
                .collect(Collectors.toCollection(
                        () -> new ArrayList<>(rates.size())));
    }

    private static CountryVat toCountryVat(Rate rate) {
        CountryVat cv = new CountryVat();
        cv.setName(rate.getName());
        cv.setCode(rate.getCode());
        cv.setCountryCode(rate.getCountryCode());

        Period newestPeriod = newestPeriod(rate.getPeriods());
        Date effectiveFrom = newestPeriod.getEffectiveFrom();
        double standardVat = standardVat(newestPeriod);

        cv.setStandardVat(standardVat);
        cv.setEffectiveFrom(effectiveFrom);

        return cv;
    }

    private static Period newestPeriod(List<Period> periods) {
        List<Period> copyOfPeriods = new ArrayList<>(periods);
        copyOfPeriods.sort(Comparator.comparing(Period::getEffectiveFrom));
        return lastOf( copyOfPeriods );
    }

    private static double standardVat(Period period) {
        return period.getRates().get(Period.STANDARD_RATE_KEY);
    }
}
