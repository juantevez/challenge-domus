package domus.challenge.adapter.in.controller;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.domain.port.in.MoviesPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external/movies")
public class MoviesController {

    private MoviesPort moviesPort;

    public MoviesController(MoviesPort moviesPort) {
        this.moviesPort = moviesPort;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = moviesPort.fetchAllMoviesFromExternalApi();
        return ResponseEntity.ok(movies);
    }

}