package domus.challenge.domain.port.in;

import domus.challenge.adapter.in.dto.MovieDto;

import java.util.List;

public interface MoviesPort {

    List<MovieDto> fetchAllMoviesFromExternalApi();


}
