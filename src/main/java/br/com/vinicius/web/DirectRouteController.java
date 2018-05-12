package br.com.vinicius.web;

import br.com.vinicius.domain.BusStations;
import br.com.vinicius.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DirectRouteController {

    private final StationService service;

    public DirectRouteController(StationService service){
        this.service = service;
    }

    @GetMapping("/direct")
    public BusStations verify(  @RequestParam("dep_sid") String departure,
                                @RequestParam("arr_sid") String arrival){
        return service.verifyStationRoute(departure,arrival);
    }


}
