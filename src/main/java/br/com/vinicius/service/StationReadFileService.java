package br.com.vinicius.service;

import br.com.vinicius.enums.EnumsHazelcastMaps;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class StationReadFileService {

    @Autowired
    @Qualifier("getStationIMDG")
    private IMap<String,Set<String>> hazel;

    public void setStations(String line){
        String[] split = line.trim().split(" ");
        if(split.length<4)
            return;
        String routeId = split[0];
        Arrays.stream(split).skip(1).forEach( v ->{
            if(!hazel.containsKey(v))
                hazel.put(v, new HashSet<>());

            Set<String> strings = hazel.get(v);
            strings.add(routeId);
            hazel.put(v,strings);
        });
    }
}
