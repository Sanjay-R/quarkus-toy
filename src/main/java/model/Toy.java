package model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
class Toy {

  @NotNull
  private int id;
  private String name;

}
