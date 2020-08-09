package com.indra.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.indra.api.utils.LocalDateFormatter;
import com.univocity.parsers.annotations.*;
import com.univocity.parsers.annotations.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_PRECO", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "DATA_COLETA"
                ,"REVENDEDORA_CNPJ"
                ,"municipio"
                ,"produto"
                ,"bandeira"
                ,"VALOR_VENDA"
                ,"VALOR_COMPRA"
        })})
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoPreco implements Serializable {

    private static final long serialVersionUID = 3312696896854165688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY, notes = "O ID gerado pela base de dados.", position = 1)
    private Long id;

    @Column(name = "DATA_COLETA")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Parsed(index = 6)
    @Format(formats = {"dd/MM/yyyy"})
    @Convert(conversionClass = LocalDateFormatter.class, args = "dd/MM/yyyy")
    @ApiModelProperty(notes = "A data da coleta da informação.", position = 2)
    private LocalDate dataColeta;

    @Parsed(index = 3)
    @ApiModelProperty(notes = "A revendedora associada ao histórico.", position = 3)
    private String revendedora;

    @Column(name = "REVENDEDORA_CNPJ")
    @Parsed(index = 4)
    @ApiModelProperty(notes = "O CNPJ da revendedora associada ao histórico.", position = 4)
    private String cnpj;

    @Parsed(index = 2)
    @ApiModelProperty(notes = "O município onde foi registrado o histórico.", position = 5)
    private String municipio;

    @Column(name = "SIGLA_ESTADO")
    @Parsed(index = 1)
    @ApiModelProperty(notes = "O estado onde foi registrado o histórico.", position = 6)
    private String siglaEstado;

    @Column(name = "SIGLA_REGIAO")
    @Parsed(index = 0)
    @ApiModelProperty(notes = "A região onde foi registrado o histórico.", position = 7)
    private String siglaRegiao;

    @Parsed(index = 5)
    @ApiModelProperty(notes = "O produto associado ao histórico.", position = 8)
    private String produto;

    @Parsed(index = 10)
    @ApiModelProperty(notes = "A bandeira associada ao histórico.", position = 9)
    private String bandeira;

    @Column(name = "VALOR_VENDA")
    @Parsed(index = 7)
    @Replace(expression = "/[\u0000]/", replacement = "")
    @Format(formats = {"#0,0000"}, options = "decimalSeparator=,")
    @ApiModelProperty(notes = "O valor da venda do produto.", position = 10)
    private Double valorVenda;

    @Column(name = "VALOR_COMPRA")
    @Parsed(index = 8)
    @Replace(expression = "/[\u0000]/", replacement = "")
    @Format(formats = {"#0,0000"}, options = "decimalSeparator=,")
    @ApiModelProperty(notes = "O valor de compra pelo produto.", position = 11)
    private Double valorCompra;

    @Column(name = "VALOR_UNIDADE_MEDICAO")
    @Parsed(index = 9)
    @ApiModelProperty(notes = "A unidade de medida do histórico.", position = 12)
    private String unidadeMedida;
}
