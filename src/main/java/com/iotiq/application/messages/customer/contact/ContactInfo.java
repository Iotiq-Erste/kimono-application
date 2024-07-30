package com.iotiq.application.messages.customer.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.Optional;

public record ContactInfo(
        @NotEmpty(message = "First name is mandatory.")
        String firstname,
        @NotEmpty(message = "Last name is mandatory.")
        String lastname,
        Optional<Salutation> salutation,
        @JsonProperty("zip_code")
        @NotNull
        String zipCode,
        @NotNull
        String city,
        @NotNull
        String street,
        Optional<Country> country,
        @NotEmpty(message = "Phone number is mandatory.")
        @Pattern(
                regexp = "^((\\+49|0)[1-9][0-9]{1,14})$",
                message = "Invalid phone number format. Please provide a valid German phone number."
        )
        String phone,
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
               message = "Invalid email format. Please provide a valid email address.")
        @NotEmpty(message = "Email is mandatory.")
        String email,
        @JsonProperty("age_group")
        Optional<AgeGroup> ageGroup,
        @JsonProperty("customer_number")
        int customerNumber,
        Optional<String> status, //Status ne demek?
        Optional<Language> language,
        @JsonProperty("delivery_address")
        @NotEmpty(message = "Delivery address is mandatory.")
        String deliveryAddress,
        @JsonProperty("delivery_street")
        @NotEmpty(message = "Delivery street is mandatory.")
        String deliveryStreet,
        @JsonProperty("delivery_post_code")
        @NotEmpty(message = "Delivery post code is mandatory.")
        String deliveryPostCode,
        @JsonProperty("delivery_city")
        @NotEmpty(message = "Delivery city is mandatory.")
        String deliveryCity,
        @JsonProperty("delivery_country")
        Optional<Country> deliveryCountry,
        @JsonProperty("delivery_difference")
        String deliveryDifference,
        String notes
        //NotEmpty yerine NotBlank daha iyi olmaz mı
) {}
