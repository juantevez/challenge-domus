package domus.challenge.adapter.out.client;

import domus.challenge.adapter.in.dto.MovieDto;
import domus.challenge.adapter.in.dto.MovieResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static domus.challenge.utils.Constants.URL_MOVIES_LIST;

@Component
public class MovieClientImpl implements MovieClient{
    private final RestTemplate restTemplate;

    public MovieClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MovieResponse get(String url, int page) {
        return restTemplate.getForObject(url, MovieResponse.class);
    }

    @Override
    public List<MovieDto> fetchAll() {
        List<MovieDto> allMovies = new ArrayList<>();
        int currentPage = 1;
        int totalPages = 1;

        while (currentPage <= totalPages) {
            String url =  URL_MOVIES_LIST + currentPage;
            MovieResponse response =  get(url, currentPage);
                    //movieClientImpl.get(url, currentPage);

            if (response == null) break;

            if (currentPage == 1) {
                totalPages = response.getTotalPages();
            }

            if (response.getData() != null && !response.getData().isEmpty()) {
                allMovies.addAll(response.getData());
            }

            currentPage++;
        }

        return allMovies;
    }
}
