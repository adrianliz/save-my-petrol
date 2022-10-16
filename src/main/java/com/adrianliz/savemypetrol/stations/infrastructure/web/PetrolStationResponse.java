package com.adrianliz.savemypetrol.stations.infrastructure.web;

import com.adrianliz.savemypetrol.common.domain.Currency;
import com.adrianliz.savemypetrol.products.domain.ProductPrice;
import com.adrianliz.savemypetrol.products.domain.ProductType;
import com.adrianliz.savemypetrol.stations.domain.PetrolStation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationId;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationLocation;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationName;
import com.adrianliz.savemypetrol.stations.domain.PetrolStationProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

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
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.REGULAR_95,
            new ProductPrice(parsePrice(regular95Price, decimalFormatter), Currency.EUR)));
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.REGULAR_95_PREMIUM,
            new ProductPrice(parsePrice(regular95PremiumPrice, decimalFormatter), Currency.EUR)));
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.REGULAR_98,
            new ProductPrice(parsePrice(regular98Price, decimalFormatter), Currency.EUR)));
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.DIESEL_A,
            new ProductPrice(parsePrice(dieselAPrice, decimalFormatter), Currency.EUR)));
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.DIESEL_A_PREMIUM,
            new ProductPrice(parsePrice(dieselAPremiumPrice, decimalFormatter), Currency.EUR)));
    petrolStationProducts.add(
        new PetrolStationProduct(
            ProductType.DIESEL_B,
            new ProductPrice(parsePrice(dieselBPrice, decimalFormatter), Currency.EUR)));

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
