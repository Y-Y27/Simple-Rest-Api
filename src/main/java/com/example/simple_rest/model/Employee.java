package com.example.simple_rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel(description = "Персональные данные по работнику")
@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Уникальный идентификатор работника")
    private Long id;

    @ApiModelProperty("Имя")
    private String firstName;

    @ApiModelProperty("Фамилия")
    private String lastName;

    @ApiModelProperty("Паспорт")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Passport passport;

    @ApiModelProperty("Адрес")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
}
