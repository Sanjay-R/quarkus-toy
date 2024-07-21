package service;

import dto.ToyRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mapper.ToyMapper;
import model.Toy;
import repository.ToyRepository;

@Slf4j
@ApplicationScoped
public class ToyService {

  @Inject
  ToyRepository repository;

  @Inject
  ToyMapper mapper;

  @Transactional
  public Toy create(ToyRequestDTO requestDTO) {
    var domain = mapper.toDomain(requestDTO);
    var entity = mapper.toEntity(domain);
    repository.persist(entity);

    return mapper.toDomain(entity);
  }

  @Transactional
  public void delete(int id) {
    var item = repository.findById(id);
    if (item.isEmpty()) {
      log.info("There was no entry present with id={}", id);
    } else {
      repository.delete("id", id);
      log.info("Deleted toy entry with id={}", id);
    }
  }


}
