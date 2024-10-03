package com.iotiq.application.messages.customer.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicInfo {

    @NotEmpty(message = "First name is mandatory.")
    private String firstname;
    private String lastname;
    @Pattern(
            regexp = "^((\\+49|0)[1-9][0-9]{1,14})$",
            message = "Invalid phone number format. Please provide a valid German phone number."
    )
    private String phoneNumber;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Invalid email format. Please provide a valid email address.")
    private String email;
}
