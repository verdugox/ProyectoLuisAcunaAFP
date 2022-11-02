package com.ProyectoLAAR.RegistroAFP.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotNull
    @Column(name="DNI", nullable = false, length = 8)
    private Integer DNI;
    @NotNull
    @DecimalMax("10000000.00") @DecimalMin("0.0")
    @Column(nullable = false, length = 50)
    private Double amountRetired;
    @NotBlank
    @Size(min = 0, max = 50)
    @Column(nullable = false, length = 50)
    private String AFP;
    @NotNull
    @Column(nullable = false)
    private Date dateRetired;
    @NotNull
    @Column(name="nroAccount", nullable = false)
    private Long nroAccount;


}
