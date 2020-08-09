package com.indra.api.resource;

import com.indra.api.dto.HistoricoPrecoPersist;
import com.indra.api.dto.ValorMedioResponse;
import com.indra.api.entity.HistoricoPreco;
import com.indra.api.service.HistoricoPrecoService;
import com.indra.api.utils.ConstantesPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Api(value = ConstantesPaths.PATH_HISTORICO, produces = MediaType.APPLICATION_JSON_VALUE, tags = "Historico")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = ConstantesPaths.PATH_HISTORICO, produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricoPrecoResource {

    @Autowired
    HistoricoPrecoService historicoPrecoService;

    @PostMapping
    @ApiOperation(value = "Cadastrar histório de preço de combustível", notes = "Este recurso permite cadastrar um histórico de preço de combustível na base de dados.", response = HistoricoPreco.class)
    public HistoricoPreco cadastrarHistoricoPreco(@Valid @RequestBody HistoricoPrecoPersist historicoPrecoPersist) {
        return historicoPrecoService.cadastrar(historicoPrecoPersist);
    }

    @GetMapping(path = {"/{id}"})
    @ApiOperation(value = "Buscar histórico de preço de combustível", notes = "Este recurso permite buscar um histórico de preço de combustível na base de dados.", response = HistoricoPreco.class)
    public HistoricoPreco buscarHistoricoPreco(@PathVariable Long id) {
        return historicoPrecoService.buscar(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar histórico de preço de combustível", notes = "Este recurso permite atualizar um histórico de preço de combustível.", response = HistoricoPreco.class)
    public HistoricoPreco atualizarUsuario(@Valid @RequestBody HistoricoPrecoPersist persist, @PathVariable Long id) {
        return historicoPrecoService.atualizar(id, persist);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar histórico de preço de combustível", notes = "Este recurso permite deletar um histórico de preço de combustível da base de dados.")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        historicoPrecoService.deletar(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Listar históricos de preço de combustível", notes = "Este recurso permite listar todos os histórico de preço de combustível.", response = HistoricoPreco.class)
    public Page<HistoricoPreco> listarHistoricos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return historicoPrecoService.listar(pageable);
    }

    @GetMapping(path = {"/municipio/media-precos"})
    @ApiOperation(value = "Calcula a média de preço de combustível, por município", notes = "Este recurso permite calcular a média de valores de compra e venda de preço de combustível de um município.", response = ValorMedioResponse.class)
    public ValorMedioResponse mediaPrecoPorMunicipio(@RequestParam(value = "municipio") String municipio) {
        return historicoPrecoService.getMediaPrecosByMunicipio(municipio);
    }


    @GetMapping(path = {"/regiao/consultar"})
    @ApiOperation(value = "Listar históricos de preço de combustível de uma região", notes = "Este recurso permite listar todos os histórico de preço de combustível de uma única região.", response = HistoricoPreco.class)
    public Page<HistoricoPreco> consultarHistoricoRegiao(
            @RequestParam(value = "regiao") String siglaRegiao,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return historicoPrecoService.listarPorRegiao(siglaRegiao, pageable);
    }

    @GetMapping(path = {"/bandeira/consultar"})
    @ApiOperation(value = "Listar históricos de preço de combustível de uma bandeira", notes = "Este recurso permite listar todos os histórico de preço de combustível de uma única bandeira.", response = HistoricoPreco.class)
    public Page<HistoricoPreco> consultarHistoricoBandeira(
            @RequestParam(value = "bandeira") String bandeira,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return historicoPrecoService.listarPorBandeira(bandeira, pageable);
    }

    @GetMapping(path = {"/bandeira/media-precos"})
    @ApiOperation(value = "Calcula a média de preço de combustível, por bandeira", notes = "Este recurso permite calcular a média de valores de compra e venda de preço de combustível de um município.", response = ValorMedioResponse.class)
    public ValorMedioResponse mediaPrecoPorBandeira(@RequestParam(value = "bandeira") String bandeira) {
        return historicoPrecoService.getMediaPrecosByBandeira(bandeira);
    }

    @GetMapping(path = {"/revendedora/consultar"})
    @ApiOperation(value = "Listar históricos de preço de combustível de uma revendedora", notes = "Este recurso permite listar todos os histórico de preço de combustível de uma única revendedora.", response = HistoricoPreco.class)
    public Page<HistoricoPreco> consultarHistoricoRevendedora(
            @RequestParam(value = "revendedora") String revendedora,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return historicoPrecoService.listarPorRevendedora(revendedora, pageable);
    }

    @GetMapping(path = {"/data/consultar"})
    @ApiOperation(value = "Listar históricos de preço de combustível de uma data", notes = "Este recurso permite listar todos os histórico de preço de combustível de uma única data.", response = HistoricoPreco.class)
    public Page<HistoricoPreco> consultarHistoricoData(
            @RequestParam(value = "dataColeta", defaultValue = "yyyy-MM-dd")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataColeta,

            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return historicoPrecoService.listarPorData(dataColeta, pageable);
    }

    @PostMapping("/importar-csv")
    @ApiOperation(value = "Importar CSV", notes = "Este recurso permite carregar os histórico de preço de combustível a partir de um arquivo CSV.", response = HistoricoPreco.class)
    public List<HistoricoPreco> importarCSV(@RequestParam(name = "arquivo") MultipartFile arquivo) {
        return historicoPrecoService.importarCsv(arquivo);
    }
}
