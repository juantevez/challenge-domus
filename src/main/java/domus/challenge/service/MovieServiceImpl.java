package domus.challenge.service;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.domain.port.in.MovieServicePort;
import domus.challenge.domain.port.in.MoviesPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieServicePort {

    private final MoviesPort moviesPort;

    public MovieServiceImpl(MoviesPort moviesPort) {
        this.moviesPort = moviesPort;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return moviesPort.fetchAllMoviesFromExternalApi();
    }

}