package factory.entity;

import repository.ToyEntity;

public abstract class ToyEntityFactory {

  public static final ToyEntity TOY_ENTITY = build();

  public static ToyEntity build() {
    return new ToyEntity();
  }
}
