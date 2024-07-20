package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ToyRequestDTO {

  @NotBlank(message = "Name may not be blank.")
  private String name;

}
