package service;

import dto.ToyRequestDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import model.Toy;
import org.junit.jupiter.api.Test;
import repository.ToyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}