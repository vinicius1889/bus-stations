package br.com.vinicius.service;

import br.com.vinicius.domain.BusStations;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StationService {

    private final IMap<String,Set<String>> imdg;

    StationService(@Qualifier("getStationIMDG") IMap<String,Set<String>> imdg){
        this.imdg = imdg;
    }

    public BusStations verifyStationRoute(String departure, String arrival){
        if(!imdg.containsKey(departure) || !imdg.containsKey(arrival))
            return createResponse(departure,arrival,false);

        Set<String> routeA = imdg.get(departure);
        Set<String> routeB = imdg.get(arrival);
        return createResponse(  departure,
                                arrival,
                                isThereAnyIntersectionAmoungRoutes(routeA,routeB));

    }

    private boolean isThereAnyIntersectionAmoungRoutes(Set<String> routeA, Set<String> routeB){
        return routeA.stream().filter(s->routeB.contains(s)).count()>0;
    }


    private BusStations createResponse(String departure, String arrival, boolean isThereRoute){
       return BusStations.builder()
                    .departure(departure)
                    .arrival(arrival)
                    .directBusRoute(isThereRoute)
                    .build();
    }

}
