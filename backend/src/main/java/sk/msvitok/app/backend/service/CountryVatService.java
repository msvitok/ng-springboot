package sk.msvitok.app.backend.service;

import sk.msvitok.app.backend.entity.CountryVat;
import sk.msvitok.app.external.jsonvat.JsonvatService;
import sk.msvitok.app.utils.Comparators;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.google.common.base.MoreObjects.firstNonNull;

@Service
public class CountryVatService {

    private final JsonvatService jsonvatService;

    @Autowired
    public CountryVatService(JsonvatService jsonvatService) {
        this.jsonvatService = jsonvatService;
    }

    /**
     * <p>LESS EFFICIENT ALTERNATIVE</p>
     *
     * <pre>
     *     1. natural order,
     *     2. a) read {@code limit} rates from start ({@code limit} lowest),
     *        b) read {@code limit} rates from end ({@code limit} highest).
     * </pre>
     *
     * In case of max limit, we will be performing O(n) operation to read all
     * items in given order, which is why we are using implemented, maybe
     * slightly less readable, solution.
     */
    public List<CountryVat> getCountryVatsWithOrder(@Nullable Boolean asc,
                                                    @Nullable Integer limit)
    {
        List<CountryVat> countryVats = jsonvatService.getCountryVats();

        boolean useNaturalOrder = firstNonNull(asc, true);

        List<CountryVat> sortedCountryVats = sortedByNaturalOrder(countryVats,
                useNaturalOrder ? Comparators.defaultOrder() : Comparator::reversed);

        if (limit == null || limit > countryVats.size()) {
            return sortedCountryVats;
        }
        return sortedCountryVats.subList(0, limit);
    }

    /* --------------------------- helper-methods --------------------------- */

    /**
     * @param orderMapper identity for natural order or {@code Comparator::reversed}.
     */
    private static List<CountryVat> sortedByNaturalOrder(List<CountryVat> countryVats,
                                                         UnaryOperator<Comparator<CountryVat>> orderMapper)
    {
        List<CountryVat> copyOfRates = new ArrayList<>(countryVats);
        Comparator<CountryVat> comparator = Comparator.comparing(CountryVat::getStandardVat);
        comparator = orderMapper.apply(comparator);
        copyOfRates.sort( comparator.thenComparing(CountryVat::getName) );
        return copyOfRates;
    }
}
