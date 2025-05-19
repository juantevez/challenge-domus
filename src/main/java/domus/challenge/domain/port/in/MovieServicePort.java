package domus.challenge.domain.port.in;

import domus.challenge.adapter.in.dto.MovieDto;

import java.util.List;

public interface MovieServicePort {
    List<MovieDto> getAllMovies();


}
