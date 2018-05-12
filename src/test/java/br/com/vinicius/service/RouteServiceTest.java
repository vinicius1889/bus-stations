package br.com.vinicius.service;

import br.com.vinicius.domain.BusStationShortRoute;
import br.com.vinicius.domain.BusStations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteServiceTest {

    @Autowired
    private RouteService service;

    @Autowired
    private ObjectMapper mapper;


    //1 153 150 148 106 17 20 160 140 24
    @Test
    public void shouldReturnShortestRoute() throws JsonProcessingException {
        BusStationShortRoute busStationShortRoute = service.verifyStationRoute("153", "140");
        String s = mapper.writeValueAsString(busStationShortRoute);
        System.out.println("s = " + s);
        Assert.assertTrue(busStationShortRoute.getRouteId()>-1);
    }
}
