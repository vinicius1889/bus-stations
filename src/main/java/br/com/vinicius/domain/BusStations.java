package br.com.vinicius.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusStations {
    @JsonProperty("dep_sid")
    private String departure;
    @JsonProperty("arr_sid")
    private String arrival;
    @JsonProperty("direct_bus_route")
    private Boolean directBusRoute;
}
