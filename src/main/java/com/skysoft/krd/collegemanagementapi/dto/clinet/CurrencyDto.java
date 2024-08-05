package com.skysoft.krd.collegemanagementapi.dto.clinet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CurrencyDto {
    @JsonProperty("CAD")
    private double CAD;
    @JsonProperty("EUR")
    private double EUR;
    @JsonProperty("USD")
    private double USD;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDto currencyDto = (CurrencyDto) o;
        return Double.compare(CAD, currencyDto.CAD) == 0 && Double.compare(EUR, currencyDto.EUR) == 0 && Double.compare(USD, currencyDto.USD) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CAD, EUR, USD);
    }
}
