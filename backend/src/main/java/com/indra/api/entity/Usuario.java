package com.indra.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 4703766681150526808L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY, notes = "O ID gerado pela base de dados.", position = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @ApiModelProperty(notes = "O nome do usuário.", position = 2)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true)
    @ApiModelProperty(notes = "O login do usuário.", position = 3)
    private String login;
}
