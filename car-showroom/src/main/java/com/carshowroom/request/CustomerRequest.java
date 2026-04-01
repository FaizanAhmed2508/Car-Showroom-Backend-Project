package com.carshowroom.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private Long customerId;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String address;

}
