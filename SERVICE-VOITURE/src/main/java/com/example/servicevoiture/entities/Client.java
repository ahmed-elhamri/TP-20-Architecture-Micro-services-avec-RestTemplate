package com.example.servicevoiture.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private Long id;
    private String nom;
    private Integer age;
}
