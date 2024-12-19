package nl.pink.pinkproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreatePersonRequest(@NotBlank String name,
                                  @JsonFormat(pattern = "yyyy/MM/dd") LocalDate birthDate,
                                  Long parent1Id,
                                  Long parent2Id,
                                  Long partnerId) {
}
