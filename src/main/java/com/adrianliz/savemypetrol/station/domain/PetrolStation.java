package com.adrianliz.savemypetrol.station.domain;

import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.station.domain.exception.InvalidPetrolStation;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PetrolStation implements Serializable {

  private final PetrolStationId id;
  private final PetrolStationName name;
  private final PetrolStationLocation location;
  private final List<PetrolStationProduct> products;

  public PetrolStation(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final List<PetrolStationProduct> products) {

    validate(id, name, location, products);
    this.id = id;
    this.name = name;
    this.location = location;
    this.products = products;
  }

  private void validate(
      final PetrolStationId id,
      final PetrolStationName name,
      final PetrolStationLocation location,
      final List<PetrolStationProduct> products) {

    if (id == null || name == null || location == null || products == null) {
      throw new InvalidPetrolStation();
    }
  }

  public PetrolStationId id() {
    return id;
  }

  public PetrolStationName name() {
    return name;
  }

  public PetrolStationLocation location() {
    return location;
  }

  public List<PetrolStationProduct> products() {
    return products;
  }

  public Optional<PetrolStationProduct> find(final ProductType product) {
    return products.stream().filter(p -> p.type().equals(product)).findFirst();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PetrolStation that = (PetrolStation) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
