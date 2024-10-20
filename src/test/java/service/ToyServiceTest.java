package service;

import dto.ToyRequestDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import domain.Toy;
import org.junit.jupiter.api.Test;
import repository.ToyRepository;

import java.util.List;

import static factory.domain.ToyDomainFactory.TOY_DOMAIN_1;
import static factory.domain.ToyDomainFactory.TOY_DOMAIN_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ToyServiceTest {

  @Inject
  ToyService toyService;

  @InjectMock
  ToyRepository toyRepository;

  @Test
  void createTest() {
    ToyRequestDTO dto = new ToyRequestDTO();
    when(toyRepository.create(dto)).thenReturn(new Toy());

    var result = toyService.create(dto);

    assertNotNull(result);
  }

  @Test
  void deleteTest() {
    int idToDelete = 2;

    toyService.delete(idToDelete);

    verify(toyRepository, times(1)).delete(idToDelete);
  }

  @Test
  void getAllTest() {
    when(toyRepository.getAll()).thenReturn(List.of(new Toy(), new Toy()));

    var result = toyService.getAll(List.of());

    assertEquals(result.size(), 2);
  }

  @Test
  void getAllByIdsTest() {
    when(toyRepository.getById(1)).thenReturn(TOY_DOMAIN_1);
    when(toyRepository.getById(2)).thenReturn(TOY_DOMAIN_2);

    var result = toyService.getAll(List.of(1, 2));

    assertThat(result.size()).isEqualTo(2);
    verify(toyRepository, times(1)).getById(1);
    verify(toyRepository, times(1)).getById(2);
  }

  @Test
  void getAllNotFoundExceptionTest() {
    when(toyRepository.getById(1)).thenReturn(null);  // Simulate no toy found
    when(toyRepository.getById(2)).thenReturn(null);

    assertThrows(NotFoundException.class, () -> toyService.getAll(List.of(1, 2)), "No entries with the given ids were found");
    verify(toyRepository, times(1)).getById(1);
    verify(toyRepository, times(1)).getById(2);
  }

  @Test
  void deleteAllTest() {
    toyService.deleteAll();

    verify(toyRepository, times(1)).deleteAll();
  }

}