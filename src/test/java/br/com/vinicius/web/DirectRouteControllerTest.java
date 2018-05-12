package br.com.vinicius.web;

import br.com.vinicius.domain.BusStations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectRouteControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private ObjectMapper mapper;



    @Test
    public void shouldReturnJsonWithTRUEDirectRoute() throws JsonProcessingException {
        Map<String,String> mapURL = new HashMap<>();
        mapURL.put("dep","150");
        mapURL.put("arr","153");
        BusStations bus = rest.getForObject("/direct?dep_sid={dep}&arr_sid={arr}", BusStations.class,mapURL);
        String s = mapper.writeValueAsString(bus);
        System.out.println("forObject = " + s);
        Assert.assertTrue(bus.getDirectBusRoute());
    }

    @Test
    public void shouldReturnJsonWithFALSEDirectRoute() throws JsonProcessingException {
        Map<String,String> mapURL = new HashMap<>();
        mapURL.put("dep","150");
        mapURL.put("arr","777");
        BusStations busStations = rest.getForObject("/direct?dep_sid={dep}&arr_sid={arr}", BusStations.class,mapURL);
        Assert.assertTrue(!busStations.getDirectBusRoute());
    }





}
