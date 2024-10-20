package factory.domain;

import domain.Toy;

public abstract class ToyDomainFactory {

  public static final Toy TOY_DOMAIN_1 = build(1, "name");

  public static final Toy TOY_DOMAIN_2 = build(2, "name2");

  public static Toy build(int id, String name) {
    var toy = new Toy();
    toy.setId(id);
    toy.setName(name);
    return toy;
  }

}
