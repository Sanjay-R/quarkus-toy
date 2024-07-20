package dto;

import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ToyResponseDTO(

    @Schema(example = "1")
    int id,

    @NotNull(message = "Name may not be null.")
    String name,

    @Schema(description = "This is a random number for the fun of it, and to test out mapping to DTO", example = "279823", maxLength = 3)
    int randomNumber
) {
}
