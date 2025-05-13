package domus.challenge.adapter.in.controller;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.service.ExternalMovieClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external/movies")
public class ExternalMovieController {

    private final ExternalMovieClient externalMovieClient;

    public ExternalMovieController(ExternalMovieClient externalMovieClient) {
        this.externalMovieClient = externalMovieClient;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = externalMovieClient.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    // Método de fallback cuando el circuito está abierto
    private ResponseEntity<List<MovieDto>> fallbackGetAllMovies(Throwable t) {
        System.err.println("Fallback method called due to: " + t.getMessage());
        return ResponseEntity.status(503).body(List.of()); // Devuelve lista vacía o caché
    }
}