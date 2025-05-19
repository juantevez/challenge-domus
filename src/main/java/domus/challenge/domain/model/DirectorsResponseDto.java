package domus.challenge.domain.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorsResponseDto {
    private List<String> directors;

}