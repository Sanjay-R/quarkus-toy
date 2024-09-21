package service;

import dto.ToyRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.Toy;
import repository.ToyRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@ApplicationScoped
public class ToyService {

  @Inject
  ToyRepository repository;

  public List<Toy> getAll(List<Integer> ids) {
    List<Toy> toys;

    if (ids.isEmpty()) {
      log.info("Getting all entries");
      toys = repository.getAll();
    } else {
      log.info("Getting entries for ids {}", ids);
      toys = ids.stream()
          .map(x -> repository.getById(x))
          .filter(Objects::nonNull)
          .toList();
      if (toys.isEmpty()) {
        log.error("No entries with the given ids were found");
        throw new NotFoundException("No entries with the given ids were found");
      }
    }

    return toys;
  }

  public Toy create(ToyRequestDTO requestDTO) {
    log.info("Creating a new toy entry");
    return repository.create(requestDTO);
  }

  public void delete(int id) {
    repository.delete(id);
    log.info("Deleted toy entry with id={}", id);
  }

  @Transactional
  public void deleteAll() {
    repository.deleteAll();
    log.info("Deleted all toy entries from the database");
  }

}
