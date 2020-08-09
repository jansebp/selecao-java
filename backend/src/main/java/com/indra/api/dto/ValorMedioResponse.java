package com.indra.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ValorMedioResponse", description = "Classe Response para o Valor Médio de Histórico de Preço.")
public class ValorMedioResponse implements Serializable {

    private static final long serialVersionUID = -6570345821913006026L;

    @ApiModelProperty(notes = "O valor médio de venda do produto.", position = 1)
    private Double valorVendaMedio;

    @ApiModelProperty(notes = "O valor médio de compra pelo produto.", position = 2)
    private Double valorCompraMedio;

    @ApiModelProperty(notes = "A média consolidada entre venda e compra do produto.", position = 3)
    private Double mediaConsolidada;
}
