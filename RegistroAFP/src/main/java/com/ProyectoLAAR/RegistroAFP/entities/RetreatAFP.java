package com.ProyectoLAAR.RegistroAFP.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "retreatsAFP")
public class RetreatAFP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRetreatAFP")
    private Integer idRetreatAFP;
    @Column(name="DNI", nullable = false, length = 8)
    private Integer DNI;
    @Column(nullable = false, length = 50)
    private Number amountRetired;
    @Column(nullable = false, length = 50)
    private String AFP;
    @Column(nullable = false)
    private Date dateRetired;
    @Column(name="nroAccount", nullable = false)
    private Integer nroAccount;


}
