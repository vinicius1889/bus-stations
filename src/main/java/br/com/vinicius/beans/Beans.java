package br.com.vinicius.beans;

import br.com.vinicius.enums.EnumsHazelcastMaps;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Beans {

    @Bean
    public IMap<String,String> getConfigIMDG(){
        return Hazelcast.newHazelcastInstance().getMap(EnumsHazelcastMaps.CONFIG.toString());
    }

    @Bean
    public IMap<String,Set<String>> getStationIMDG(){
        return Hazelcast.newHazelcastInstance().getMap(EnumsHazelcastMaps.STATIONS.toString());
    }


}
