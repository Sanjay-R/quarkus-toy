package model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
class Toy {

  @NotNull
  private String id;
  private String name;

}
