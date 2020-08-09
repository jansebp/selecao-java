package com.indra.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ApiModel(value = "UsuarioPersist", description = "Classe Persist para o Usuário.")
public class UsuarioPersist implements Serializable {

    private static final long serialVersionUID = 3206988957043794742L;

    @ApiModelProperty(required = true, name = "Nome", value = "Nome do usuário", position = 1)
    @NotEmpty(message = "Campo obrigatório")
    private String nome;

    @ApiModelProperty(required = true, name = "Login", value = "Login do usuário", position = 2)
    @NotEmpty(message = "Campo obrigatório")
    private String login;
}
