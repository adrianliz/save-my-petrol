package com.adrianliz.savemypetrol.station.infrastructure.web;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.product.domain.ProductPrice;
import com.adrianliz.savemypetrol.product.domain.ProductType;
import com.adrianliz.savemypetrol.station.domain.PetrolStation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationId;
import com.adrianliz.savemypetrol.station.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.station.domain.PetrolStationName;
import com.adrianliz.savemypetrol.station.domain.PetrolStationProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@AllArgsConstructor
@NoArgsConstructor(force = true) // NOTE: Needed for Jackson
@Getter
public class PetrolStationResponse {

  @JsonProperty("IDEESS")
  private final Long id;

  @JsonProperty("Rótulo")
  private final String name;

  @JsonProperty("Latitud")
  private final String latitude;

  @JsonProperty("Longitud (WGS84)")
  private final String longitude;

  @JsonProperty("Dirección")
  private final String address;

  @JsonProperty("Precio Gasolina 95 E5")
  private final String regular95Price;

  @JsonProperty("Precio Gasolina 95 E5 Premium")
  private final String regular95PremiumPrice;

  @JsonProperty("Precio Gasolina 98 E5")
  private final String regular98Price;

  @JsonProperty("Precio Gasoleo A")
  private final String dieselAPrice;

  @JsonProperty("Precio Gasoleo Premium")
  private final String dieselAPremiumPrice;

  @JsonProperty("Precio Gasoleo B")
  private final String dieselBPrice;

  @SneakyThrows
  private Long parsePrice(final String price, final DecimalFormat decimalFormatter) {
    return (price == null || price.isEmpty() || price.isBlank())
        ? null
        : Math.round(decimalFormatter.parse(price).doubleValue() * 1000);
  }

  @SneakyThrows
  public PetrolStation mapToPetrolStation() {
    final DecimalFormat decimalFormatter = new DecimalFormat();

    final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator(',');
    symbols.setGroupingSeparator(' ');
    decimalFormatter.setDecimalFormatSymbols(symbols);

    final List<PetrolStationProduct> petrolStationProducts = new ArrayList<>();

    final var regular95ParsedPrice = parsePrice(regular95Price, decimalFormatter);
    if (regular95ParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.REGULAR_95, new ProductPrice(regular95ParsedPrice, Currency.EUR)));
    }
    final var regular95PremiumParsedPrice = parsePrice(regular95PremiumPrice, decimalFormatter);
    if (regular95PremiumParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.REGULAR_95_PREMIUM,
              new ProductPrice(regular95PremiumParsedPrice, Currency.EUR)));
    }
    final var regular98ParsedPrice = parsePrice(regular98Price, decimalFormatter);
    if (regular98ParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.REGULAR_98, new ProductPrice(regular98ParsedPrice, Currency.EUR)));
    }
    final var dieselAParsedPrice = parsePrice(dieselAPrice, decimalFormatter);
    if (dieselAParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.DIESEL_A, new ProductPrice(dieselAParsedPrice, Currency.EUR)));
    }
    final var dieselAPremiumParsedPrice = parsePrice(dieselAPremiumPrice, decimalFormatter);
    if (dieselAPremiumParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.DIESEL_A_PREMIUM,
              new ProductPrice(dieselAPremiumParsedPrice, Currency.EUR)));
    }
    final var dieselBParsedPrice = parsePrice(dieselBPrice, decimalFormatter);
    if (dieselBParsedPrice != null) {
      petrolStationProducts.add(
          new PetrolStationProduct(
              ProductType.DIESEL_B, new ProductPrice(dieselBParsedPrice, Currency.EUR)));
    }

    return new PetrolStation(
        new PetrolStationId(id),
        new PetrolStationName(name),
        new PetrolStationLocation(
            decimalFormatter.parse(latitude).doubleValue(),
            decimalFormatter.parse(longitude).doubleValue(),
            address),
        petrolStationProducts);
  }
}
