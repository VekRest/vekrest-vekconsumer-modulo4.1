package com.vekrest.vekconsumer.vekconsumer.entities;

import java.time.LocalDate;

public record Client(
        String name,
        LocalDate birth,
        Address address
) {}