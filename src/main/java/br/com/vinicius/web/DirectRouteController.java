package br.com.vinicius.web;

import br.com.vinicius.domain.BusStations;
import br.com.vinicius.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/direct")
@Api(value="Direct Route Services", description = "Show direct routes")
public class DirectRouteController {

    private final StationService service;

    public DirectRouteController(StationService service){
        this.service = service;
    }

    @GetMapping("")
    @ApiOperation(value = "Service that verify all direct routes. I still using String just because we don't use the identifications for any calculations.")
    public BusStations verify(  @RequestParam("dep_sid") String departure,
                                @RequestParam("arr_sid") String arrival){
        return service.verifyStationRoute(departure,arrival);
    }


}
