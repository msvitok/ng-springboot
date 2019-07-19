package sk.msvitok.app.web.rest.controller;

import sk.msvitok.app.backend.entity.CountryVat;
import sk.msvitok.app.backend.service.CountryVatService;
import sk.msvitok.app.web.rest.dto.CountryVatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("countryVats")
public class CountryVatController extends BaseController {

    private final CountryVatService countryVatService;

    @Autowired
    public CountryVatController(CountryVatService countryVatService) {
        this.countryVatService = countryVatService;
    }

    @GetMapping(path="/list", produces = "application/json")
    public List<CountryVatDto> getList(@PathParam("asc") Boolean asc,
                                       @PathParam("limit") Integer limit)
    {
        List<CountryVat> list = countryVatService.getCountryVatsWithOrder(asc, limit);
        return mapperFacade.mapAsList(list, CountryVatDto.class);
    }
}