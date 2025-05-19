package domus.challenge.domain.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ThresholdRequest {

    @Min(value = 1, message = "The threshold must be greater than 0")
    @Digits(integer = 2, message = "The threshold must be an integer value", fraction = 0)
    private int threshold;
}