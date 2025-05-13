package domus.challenge.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExternalMovieResponse {
    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int per_page;
    @JsonProperty("total")
    private int total;
    @JsonProperty("total_pages")
    private int total_pages;
    @JsonProperty("data")
    private List<MovieDto> data;
}