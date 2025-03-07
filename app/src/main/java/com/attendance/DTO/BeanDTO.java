package com.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BeanDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("in_stock")
    private boolean inStock;

    @NotNull
    // @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    @JsonProperty("price")
    private double price;

    @NotNull
    //@Min(value = 0, message = "Sold count must be at least 0")
    @PositiveOrZero(message = "Sold must be greater than or equal to 0")
    @JsonProperty("sold")
    private int sold;

    @NotNull
    @PastOrPresent(message = "Publish date must be in the past or present")
    @JsonProperty("publish_date")
    private LocalDateTime publishDate;
}
