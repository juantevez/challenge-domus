package domus.challenge.adapter.out.controller;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.adapter.out.dto.DirectorsResponseDto;
import domus.challenge.adapter.out.dto.ThresholdRequest;
import domus.challenge.service.ExternalMovieClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MovieControllerTest {

    @Mock
    private ExternalMovieClient externalMovieClient;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MovieDto createMovie(String title, String director) {
        MovieDto movie = new MovieDto();
        movie.setTitle(title);
        movie.setDirector(director);
        return movie;
    }
    @Test
    void testGetDirectorsWithMoreMoviesThan_ReturnsFilteredDirectors() {
        // Arrange
        ThresholdRequest request = new ThresholdRequest();
        request.setThreshold(2);

        List<MovieDto> allMovies = Arrays.asList(
                createMovie("Movie 1", "Christopher Nolan"),
                createMovie("Movie 2", "Christopher Nolan"),
                createMovie("Movie 3", "Quentin Tarantino"),
                createMovie("Movie 4", "Martin Scorsese")
        );

        when(externalMovieClient.getAllMovies()).thenReturn(allMovies);

        // Act
        ResponseEntity<DirectorsResponseDto> response = movieController.getDirectorsWithMoreMoviesThan(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        //List<String> expectedDirectors = Collections.singletonList("");
        //assertEquals(expectedDirectors, response.getBody().getDirectors());
    }
}