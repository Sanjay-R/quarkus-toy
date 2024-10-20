package service;

import dto.ToyRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import domain.Toy;
import repository.ToyRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@ApplicationScoped
public class ToyService {

  @Inject
  ToyRepository toyRepository;

  public List<Toy> getAll(List<Integer> ids) {
    List<Toy> toys;

    if (ids.isEmpty()) {
      log.info("Getting all entries");
      toys = toyRepository.getAll();
    } else {
      log.info("Getting entries for ids {}", ids);
      toys = ids.stream()
          .map(x -> toyRepository.getById(x))
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
    return toyRepository.create(requestDTO);
  }

  public void delete(int id) {
    toyRepository.delete(id);
    log.info("Deleted toy entry with id={}", id);
  }

  @Transactional
  public void deleteAll() {
    toyRepository.deleteAll();
    log.info("Deleted all toy entries from the database");
  }

}
