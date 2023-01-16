package com.example.apibasic.validate;


import javax.validation.constraints.NotBlank;

public class Address {

    @NotBlank
    private String street;
    @NotBlank
    private String postCode;

}
