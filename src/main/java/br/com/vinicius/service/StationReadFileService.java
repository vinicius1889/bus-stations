package br.com.vinicius.service;

import br.com.vinicius.service.iservice.IReadFile;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class StationReadFileService  implements IReadFile{

    @Autowired
    @Qualifier("getStationIMDG")
    private IMap<String,Set<String>> stationsMap;

    public void readStringFromFile(String line){
        String[] split = line.trim().split(" ");
        if(split.length<4)
            return;
        String routeId = split[0];
        Arrays.stream(split).skip(1).forEach( v ->{
            if(!stationsMap.containsKey(v))
                stationsMap.put(v, new HashSet<>());

            Set<String> strings = stationsMap.get(v);
            strings.add(routeId);
            stationsMap.put(v,strings);
        });
    }
}
