package dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ToyRequestDTO {

  @NotNull(message = "Name may not be null.")
  private String name;

}
