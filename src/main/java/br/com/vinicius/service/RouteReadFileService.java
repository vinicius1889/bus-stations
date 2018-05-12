package br.com.vinicius.service;

import br.com.vinicius.service.iservice.IReadFile;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteReadFileService  implements IReadFile {
    @Autowired
    @Qualifier("getRoutesIMDG")
    private IMap<String,String> routesMap;

    public void readStringFromFile(String line){
        String[] split = line.trim().split(" ");
        if(split.length<4)
            return;
        String routeId = split[0];
        String newLineWithoutRouteId = Arrays.stream(line.split(" "))
                                            .skip(1)
                                            .collect(Collectors.joining(" "));
        routesMap.put(routeId,newLineWithoutRouteId);
    }


}
