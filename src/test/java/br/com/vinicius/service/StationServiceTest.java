package br.com.vinicius.service;

import br.com.vinicius.domain.BusStations;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StationServiceTest {

    @Autowired
    private StationService service;

    //1 153 150 148 106 17 20 160 140 24
    @Test
    public void testSimpleDirectRouteToBeTrue(){
        BusStations busStations = service.verifyStationRoute("153", "140");
        Assert.assertTrue(busStations.getDirectBusRoute());
    }

    //1 153 150 148 106 17 20 160 140 24
    @Test
    public void testSimpleDirectRouteToBeFalse(){
        BusStations busStations = service.verifyStationRoute("153", "777");
        Assert.assertTrue(!busStations.getDirectBusRoute());
    }



}
