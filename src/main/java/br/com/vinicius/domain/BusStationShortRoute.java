package br.com.vinicius.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusStationShortRoute {
    @JsonProperty("dep_sid")
    private String departure;
    @JsonProperty("arr_sid")
    private String arrival;
    @JsonProperty("route_id")
    private String routeId;
    @JsonProperty("direction")
    private String direction;
}
