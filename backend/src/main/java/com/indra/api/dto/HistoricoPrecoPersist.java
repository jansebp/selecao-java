package com.indra.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@ApiModel(value = "HistoricoPrecoPersist", description = "Classe Persist para o Histórico de Preço.")
public class HistoricoPrecoPersist implements Serializable {

    private static final long serialVersionUID = 1282363716683556789L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(required = true, name = "Data da Coleta", value = "A data da coleta da informação.", position = 1)
    @NotNull(message = "Campo obrigatório")
    private LocalDate dataColeta;

    @ApiModelProperty(required = true, name = "Revendedora", value = "A revendedora associada ao histórico.", position = 2)
    @NotEmpty(message = "Campo obrigatório")
    private String revendedora;

    @ApiModelProperty(required = true, name = "CNPJ da Revendedora", value = "O CNPJ da revendedora associada ao histórico.", position = 3)
    @NotEmpty(message = "Campo obrigatório")
    private String cnpj;

    @ApiModelProperty(required = true, name = "Município", value = "O município onde foi registrado o histórico.", position = 4)
    @NotEmpty(message = "Campo obrigatório")
    private String municipio;

    @ApiModelProperty(required = true, name = "Estado", value = "A sigla do estado onde foi registrado o histórico.", position = 5)
    @NotEmpty(message = "Campo obrigatório")
    private String siglaEstado;

    @ApiModelProperty(required = true, name = "Região", value = "A sigla da região onde foi registrado o histórico.", position = 6)
    @NotEmpty(message = "Campo obrigatório")
    private String siglaRegiao;

    @ApiModelProperty(required = true, name = "Produto", value = "O produto associado ao histórico.", position = 7)
    @NotEmpty(message = "Campo obrigatório")
    private String produto;

    @ApiModelProperty(required = true, name = "Bandeira", value = "A bandeira associada ao histórico.", position = 8)
    @NotEmpty(message = "Campo obrigatório")
    private String bandeira;

    @ApiModelProperty(required = true, name = "Valor da Venda", value = "O valor da venda do produto.", position = 9)
    @NotNull(message = "Campo obrigatório")
    private Double valorVenda;

    @ApiModelProperty(required = true, name = "Valor da Compra", value = "O valor de compra pelo produto.", position = 10)
    @NotNull(message = "Campo obrigatório")
    private Double valorCompra;

    @ApiModelProperty(required = true, name = "Unidade de Medida", value = "A unidade de medida do histórico.", example = "R$ / litro" , position = 11)
    @NotEmpty(message = "Campo obrigatório")
    private String unidadeMedida;

}
