package model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Toy {

  @NotBlank
  private Integer id;

  private String name;

}
