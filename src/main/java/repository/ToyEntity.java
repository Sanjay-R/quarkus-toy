package repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Getter
@Setter
@Table(name = "toy")
public class ToyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "random_number", nullable = false)
  @Schema(description = "This is a random number for the fun of it, and to test out mapping to DTO", example = "279", maxLength = 3)
  private int randomNumber;

}
