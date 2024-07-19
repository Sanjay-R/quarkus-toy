package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ToyResponseDTO(

        @NotBlank
        @Schema(example = "1")
        String id,

        @NotNull(message = "Name may not be null.")
        String name,

        int randomNumberForTheLol
) {
}
