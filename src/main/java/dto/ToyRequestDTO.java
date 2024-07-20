package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ToyRequestDTO {

  @NotBlank
  @Schema(example = "1")
  private String id;

  @NotNull(message = "Name may not be null.")
  private String name;

}
