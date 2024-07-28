package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ToyRepository implements PanacheRepositoryBase<ToyEntity, Integer> {

  public Optional<ToyEntity> getById(int id) {
    return findByIdOptional(id);
  }

  //todo: test this out
  public List<ToyEntity> getByIds(List<Integer> ids) {
    return find("id", ids).stream().toList();
  }

  public List<ToyEntity> getAll() {
    return findAll().stream().toList();
  }

}
