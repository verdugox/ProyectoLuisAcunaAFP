package com.ProyectoLAAR.RegistroAFP.entities;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Client {


    private Integer idClient;

    private String firstName;

    private String lastName;

    private Integer DNI;

    private Integer phone;

    private String email;

    private String AFP;

    private Double amountAvailable;

}
