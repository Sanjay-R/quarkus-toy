package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ToyRepository implements PanacheRepositoryBase<ToyEntity, Integer> {

  public Optional<ToyEntity> findById(int id) {
    return find("id", id).firstResultOptional();
  }

}
