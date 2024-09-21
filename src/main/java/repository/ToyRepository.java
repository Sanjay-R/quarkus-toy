package repository;

import dto.ToyRequestDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.ToyMapper;
import model.Toy;

import java.util.List;

@ApplicationScoped
public class ToyRepository implements PanacheRepositoryBase<ToyEntity, Integer> {

  @Inject
  ToyMapper mapper;

  public Toy getById(int id) {
    return mapper.toDomain(findByIdOptional(id).orElse(null));
  }

  public List<Toy> getAll() {
    return mapper.toDomain(findAll().stream().toList());
  }

  @Transactional
  public Toy create(ToyRequestDTO requestDTO) {
    var domain = mapper.toDomain(requestDTO);
    var entity = mapper.toEntity(domain);

    persist(entity);
    return mapper.toDomain(entity);
  }

  @Transactional
  public void delete(int id) {
    deleteById(id);
  }

}
