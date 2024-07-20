package mapper;

import dto.ToyResponseDTO;
import model.Toy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Random;

@Mapper(componentModel = "cdi", uses = ToyMapper.RandomUtil.class)
public interface ToyMapper {

  @Mapping(target = "randomNumber", expression = "java(RandomUtil.generateRandom3DigitNumber())")
  ToyResponseDTO toDto(Toy domain);

  class RandomUtil {
    static final Random RANDOM = new Random();

    /**
     * Generates a random 3-digit number. Thus, between 100 and 999.
     * @return A number between 100 - 999.
     */
    static int generateRandom3DigitNumber() {
      return 100 + RANDOM.nextInt(900);
    }
  }

}
