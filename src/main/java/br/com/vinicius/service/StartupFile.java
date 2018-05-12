package br.com.vinicius.service;

import com.hazelcast.core.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class StartupFile {

    @Value("${route.location.data}")
    private String address;


    private final IMap<String,String> imdg;
    private final StationReadFileService stationService;
    private final RouteReadFileService   routeService;

    StartupFile(@Qualifier("getConfigIMDG") IMap<String,String> instance,
                StationReadFileService service,
                RouteReadFileService routeService
                ){
        this.imdg = instance;
        this.stationService = service;
        this.routeService = routeService;
    }


    public void readFile() throws IOException {
        if(!this.isAlreadyRead())
            this.setAllStateInformation();
    }

    private void setAllStateInformation() throws IOException {
        Stream<String> lines = Files.newBufferedReader(Paths.get(address)).lines();
        lines.forEach(s->{
            stationService.readStringFromFile(s);
            routeService.readStringFromFile(s);
        });
        imdg.put("hash",this.getMD5());
    }

    private String getMD5() throws IOException {
        return DigestUtils.md5DigestAsHex(Files.readAllBytes(Paths.get(address)));
    }

    public boolean isAlreadyRead() throws IOException {
        String md5 = this.getMD5();
        if(imdg.containsKey("hash") && imdg.get("hash").equals(md5))
            return true;
        return false;
    }


}
