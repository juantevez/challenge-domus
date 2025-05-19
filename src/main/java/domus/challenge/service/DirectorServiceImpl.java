package domus.challenge.service;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.domain.model.ThresholdRequest;
import domus.challenge.domain.port.out.DirectorServicePort;
import domus.challenge.domain.port.in.MovieServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorServicePort {
    private final MovieServicePort movieServicePort;

    public DirectorServiceImpl(MovieServicePort movieServicePort) {
        this.movieServicePort = movieServicePort;
    }
    @Override
    public List<String> getDirectorsWithMoreMoviesThan(ThresholdRequest threshold) {
        List<MovieDto> allMovies = movieServicePort.getAllMovies();

        Map<String, Long> directorMovieCount = allMovies.stream()
                .collect(Collectors.groupingBy(MovieDto::getDirector, Collectors.counting()));

        // Filter by threshold parameter
        List<String>filteredDirectors = directorMovieCount.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold.getThreshold())
                .map(java.util.Map.Entry::getKey)
                .sorted()
                 .collect(Collectors.toList());

        return filteredDirectors;

    }
}