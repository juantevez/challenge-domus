package domus.challenge.service;

import domus.challenge.adapter.in.dto.ExternalMovieResponse;
import domus.challenge.adapter.in.dto.MovieDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalMovieClient {

    private static final String BASE_URL = "https://challenge.iugolabs.com/api/movies/search?page=";
    private final RestTemplate restTemplate;

    public ExternalMovieClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalMovieResponse getMoviesByPage(int pageNumber) {
        String url = BASE_URL + pageNumber;
        return restTemplate.getForObject(url, ExternalMovieResponse.class);
    }

    // Nuevo método: obtiene TODAS las películas recorriendo todas las páginas
    public List<MovieDto> getAllMovies() {
        List<MovieDto> allMovies = new ArrayList<>();
        int currentPage = 1;
        int totalPages = 1;

        while (currentPage <= totalPages) {
            ExternalMovieResponse response = getMoviesByPage(currentPage);

            if (currentPage == 1) {
                totalPages = response.getTotal_pages(); // Se obtiene solo en la primera página
            }

            if (response.getData() != null && !response.getData().isEmpty()) {
                allMovies.addAll(response.getData());
            }

            currentPage++;
        }

        return allMovies;
    }
}