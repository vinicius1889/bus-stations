package br.com.vinicius.service;

import br.com.vinicius.domain.BusStationShortRoute;
import br.com.vinicius.domain.BusStations;
import br.com.vinicius.enums.EnumsTravelDirection;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {

    private final IMap<String,String> routesMap;

    private final StationService stationService;

    @Value("${route.message.success}")
    private String success;
    @Value("${route.message.error}")
    private String error;
    @Value("${route.message.hystrix}")
    private String errorHystrix;

    RouteService(StationService stationService,
                 @Qualifier("getRoutesIMDG")  IMap<String,String> routesMap) {
        this.routesMap = routesMap;
        this.stationService = stationService;
    }


    public BusStationShortRoute verifyStationRoute(String departure, String arrival) {
        Set<String> intersectionRoutes = stationService.getIntersectionRoutes(departure, arrival);
        if(intersectionRoutes==null)
            return createBusStationShortError(departure,arrival);
        return this.getShortestRouteId(departure, arrival,intersectionRoutes);
    }

    private BusStationShortRoute getShortestRouteId(String departure, String arrival, Set<String> intersectionRoutes){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addAll(intersectionRoutes);

        String shortestRouteId="";
        int shortestDiff=Integer.MAX_VALUE;
        EnumsTravelDirection enumsTravelDirection=EnumsTravelDirection.NOT_FOUND;

        for(String routeID : linkedList){
            List<String> stations = Arrays.asList(routesMap.get(routeID).split(" "));
            final int diff = stations.indexOf(departure) - stations.indexOf(arrival);
            final int diffAbs = Math.abs(diff);
            if(diffAbs<shortestDiff){
                shortestDiff=diffAbs;
                shortestRouteId=routeID;
                enumsTravelDirection = diff<0?EnumsTravelDirection.TO_RIGHT:EnumsTravelDirection.TO_LEFT;
            }
        }
        return this.createBusStationShortSuccess(departure,arrival,shortestRouteId,shortestDiff,enumsTravelDirection);
    }


    private BusStationShortRoute createBusStationShortError(String departure,String arrival){
        final String msg = error
                                .replace("[ARRIVE]",arrival)
                                .replace("[DEPARTURE]",departure);

        return BusStationShortRoute.builder()
                                .departure(departure)
                                .arrival(arrival)
                                .routeId(null)
                                .direction(msg)
                                .build();
    }
    public BusStationShortRoute createBusStationShortErrorByHystrix(String departure,String arrival){
        return BusStationShortRoute.builder()
                .departure(departure)
                .arrival(arrival)
                .routeId(null)
                .direction(errorHystrix)
                .build();

    }

    private BusStationShortRoute createBusStationShortSuccess(
                                                        String departure,
                                                        String arrival,
                                                        String routeId,
                                                        int difference,
                                                        EnumsTravelDirection direction
                                                       ){
        final String[] split = routesMap.get(routeId).split(" ");
        String stationEnd= split[0];
        if(EnumsTravelDirection.TO_RIGHT==direction)
            stationEnd=split[split.length-1];

        final String msg = success
                                .replace("[STATION_END]",stationEnd)
                                .replace("[ROUTE_ID]",routeId)
                                .replace("[STATIONS]",String.valueOf(difference));
        return BusStationShortRoute.builder()
                .departure(departure)
                .arrival(arrival)
                .routeId(routeId)
                .direction(msg)
                .build();
    }
}
