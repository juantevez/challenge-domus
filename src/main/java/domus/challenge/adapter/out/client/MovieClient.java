package domus.challenge.adapter.out.client;

import domus.challenge.adapter.in.dto.MovieDto;

import java.util.List;

public interface MovieClient {
    List<MovieDto> fetchAll();
}