package repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToyEntity {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int randomNumber;

}
