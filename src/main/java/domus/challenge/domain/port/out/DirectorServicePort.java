package domus.challenge.domain.port.out;

import domus.challenge.domain.model.ThresholdRequest;

import java.util.List;

public interface DirectorServicePort {

    List<String> getDirectorsWithMoreMoviesThan(ThresholdRequest threshold);
}
