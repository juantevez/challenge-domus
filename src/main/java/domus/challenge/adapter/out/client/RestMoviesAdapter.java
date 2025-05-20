package domus.challenge.adapter.out.client;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.domain.port.in.MoviesPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestMoviesAdapter implements MoviesPort {

    private final MovieClientImpl movieClientImpl;

    public RestMoviesAdapter(MovieClientImpl movieClientImpl) {
        this.movieClientImpl = movieClientImpl;
    }

    @Override
    public List<MovieDto> fetchAllMoviesFromExternalApi() {
        return movieClientImpl.fetchAll();
    }

}