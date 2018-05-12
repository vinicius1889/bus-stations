package br.com.vinicius.web;


import br.com.vinicius.domain.BusStationShortRoute;
import br.com.vinicius.domain.BusStations;
import br.com.vinicius.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short")
@Api(value="Shortly Route Services", description = "Show direct shortly routes")
public class ShortlyRouteController {

    private final  RouteService service;

    public ShortlyRouteController(RouteService service){
        this.service = service;
    }

    @GetMapping("")
    @ApiOperation(value = "Service that verify all short routes. I still using String just because we don't use the identifications for any calculations.")
    public BusStationShortRoute verify(@RequestParam("dep_sid") String departure,
                                       @RequestParam("arr_sid") String arrival){
        return service.verifyStationRoute(departure,arrival);
    }



}

