package domus.challenge.service;

import domus.challenge.adapter.in.dto.ExternalMovieResponse;
import domus.challenge.adapter.in.dto.MovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class ExternalMovieClientTest {

    private ExternalMovieClient externalMovieClient;
    private ExternalMovieResponse responsePage1;
    private ExternalMovieResponse responsePage2;

    private MovieDto createMovie(String title) {
        MovieDto movie = new MovieDto();
        movie.setTitle(title);
        return movie;
    }
    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        externalMovieClient = Mockito.spy(new ExternalMovieClient(restTemplate));

        // Preparar respuesta página 1
        responsePage1 = new ExternalMovieResponse();
        responsePage1.setTotal_pages(2);
        List<MovieDto> dataPage1 = new ArrayList<>();
        dataPage1.add(createMovie("Movie 1"));
        dataPage1.add(createMovie("Movie 2"));
        responsePage1.setData(dataPage1);

        // Preparar respuesta página 2
        responsePage2 = new ExternalMovieResponse();
        responsePage2.setTotal_pages(2);
        List<MovieDto> dataPage2 = new ArrayList<>();
        dataPage2.add(createMovie("Movie 3"));
        responsePage2.setData(dataPage2);

        // Mockear llamadas al método getMoviesByPage
        doReturn(responsePage1).when(externalMovieClient).getMoviesByPage(1);
        doReturn(responsePage2).when(externalMovieClient).getMoviesByPage(2);
    }
    @Test
    void getMoviesByPage() {
    }

    @Test
    void getAllMovies() {
    }

    @Test
    void testGetAllMovies_EmptyDataOnSecondPage_ShouldSkip() {
        // Configurar segunda página con data vacía
        ExternalMovieResponse responsePage2Empty = new ExternalMovieResponse();
        responsePage2Empty.setTotal_pages(2);
        responsePage2Empty.setData(new ArrayList<>());

        doReturn(responsePage1).when(externalMovieClient).getMoviesByPage(1);
        doReturn(responsePage2Empty).when(externalMovieClient).getMoviesByPage(2);

        List<MovieDto> result = externalMovieClient.getAllMovies();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllMovies_ReturnsCombinedDataFromAllPages() {
        // Act
        List<MovieDto> result = externalMovieClient.getAllMovies();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Movie 1", result.get(0).getTitle());
        assertEquals("Movie 2", result.get(1).getTitle());
        assertEquals("Movie 3", result.get(2).getTitle());
    }
}