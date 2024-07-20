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
    log.info("Creating toy entry with name {}", requestDTO.getName());

    var domain = mapper.toDomain(requestDTO);
    var entity = mapper.toEntity(domain);
    repository.persist(entity);

    return mapper.toDomain(entity);
  }


}
