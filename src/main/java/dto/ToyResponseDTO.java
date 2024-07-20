package dto;

import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ToyResponseDTO(

    @Schema(example = "1")
    int id,

    @NotNull(message = "Name may not be null.")
    String name

) {
}
