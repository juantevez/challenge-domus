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
        /*List<MovieDto> allMovies = new ArrayList<>();
        int currentPage = 1;
        int totalPages = 1;

        while (currentPage <= totalPages) {
            String url = "https://challenge.iugolabs.com/api/movies/search?page= " + currentPage;
            MovieResponse response = movieClientImpl.get(url, currentPage);

            if (response == null) break;

            if (currentPage == 1) {
                totalPages = response.getTotalPages();
            }

            if (response.getData() != null && !response.getData().isEmpty()) {
                allMovies.addAll(response.getData());
            }

            currentPage++;
        }

        return allMovies;*/
}