package domus.challenge.adapter.out.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domus.challenge.domain.model.DirectorsResponseDto;
import domus.challenge.domain.model.ThresholdRequest;
import domus.challenge.domain.port.out.DirectorServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Validated
public class DirectorController {
    private final DirectorServicePort directorServicePort;
    private final ObjectMapper objectMapper;

    public DirectorController(DirectorServicePort directorServicePort, ObjectMapper objectMapper) {
        this.directorServicePort = directorServicePort;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/directors")
    public ResponseEntity<String>  getDirectorsWithMoreMoviesThan(
             @Valid ThresholdRequest request) throws JsonProcessingException {

        List<String> directors = directorServicePort.getDirectorsWithMoreMoviesThan(request);

        // Crear y devolver el DTO
        DirectorsResponseDto response = new DirectorsResponseDto();
        response.setDirectors(directors);

        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error serializing response");
        }

    }
}