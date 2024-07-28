package service;

import dto.ToyRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import mapper.ToyMapper;
import model.Toy;
import repository.ToyEntity;
import repository.ToyRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@ApplicationScoped
public class ToyService {

  @Inject
  ToyRepository repository;

  @Inject
  ToyMapper mapper;

  @Transactional
  public List<Toy> getAll(List<Integer> ids) {
    List<ToyEntity> entities;

    if (ids.isEmpty()) {
      log.info("Getting all entries");
      entities = repository.getAll();
    } else {
      log.info("Getting entries for ids {}", ids);
      entities = ids.stream()
          .map(x -> repository.getById(x).orElse(null))
          .filter(Objects::nonNull)
          .toList();
      if (entities.isEmpty()) {
        log.error("No entries with the given ids were found");
        throw new NotFoundException("No entries with the given ids were found");
      }
    }

    return mapper.toDomain(entities);
  }

  @Transactional
  public Toy create(ToyRequestDTO requestDTO) {
    var domain = mapper.toDomain(requestDTO);
    var entity = mapper.toEntity(domain);
    repository.persist(entity);

    return mapper.toDomain(entity);
  }

  @Transactional
  public void delete(int id) {
    var item = repository.getById(id);

    if (item.isEmpty()) {
      log.info("There was no entry present with id={}", id);
    } else {
      repository.delete("id", id);
      log.info("Deleted toy entry with id={}", id);
    }
  }


}
