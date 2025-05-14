package domus.challenge.adapter.out.controller;

import domus.challenge.adapter.out.dto.DirectorsResponseDto;
import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.adapter.out.dto.ThresholdRequest;
import domus.challenge.service.ExternalMovieClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@Validated
public class MovieController {

    private final ExternalMovieClient externalMovieClient;

    public MovieController(ExternalMovieClient externalMovieClient) {
        this.externalMovieClient = externalMovieClient;
    }

    @GetMapping("/directors")
    public ResponseEntity<DirectorsResponseDto> getDirectorsWithMoreMoviesThan(
             @Valid ThresholdRequest request) {

        List<MovieDto> allMovies = externalMovieClient.getAllMovies();

        // Counting how many movies has every director
        Map<String, Long> directorMovieCount = allMovies.stream()
                .collect(Collectors.groupingBy(MovieDto::getDirector, Collectors.counting()));

        // Filter by threshold parameter
        String filteredDirectors = directorMovieCount.entrySet().stream()
                .filter(entry -> entry.getValue() > request.getThreshold())
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList()).toString();

        DirectorsResponseDto response = new DirectorsResponseDto(filteredDirectors);

        return ResponseEntity.ok(response);
    }
}