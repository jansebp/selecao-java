package com.indra.api.service;

import com.indra.api.dto.HistoricoPrecoPersist;
import com.indra.api.dto.ValorMedioResponse;
import com.indra.api.entity.HistoricoPreco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface HistoricoPrecoService {

    HistoricoPreco cadastrar(HistoricoPrecoPersist historicoPrecoPersist);

    HistoricoPreco buscar(Long id);

    HistoricoPreco atualizar(Long id, HistoricoPrecoPersist historicoPrecoPersist);

    void deletar(Long id);

    Page<HistoricoPreco> listarPorRegiao(String siglaRegiao, Pageable pageable);

    Page<HistoricoPreco> listarPorBandeira(String bandeira, Pageable pageable);

    Page<HistoricoPreco> listarPorRevendedora(String revendedora, Pageable pageable);

    Page<HistoricoPreco> listarPorData(LocalDate dataColeta, Pageable pageable);

    Page<HistoricoPreco> listar(Pageable pageable);

    ValorMedioResponse getMediaPrecosByMunicipio(String municipio);

    ValorMedioResponse getMediaPrecosByBandeira(String bandeira);

    List<HistoricoPreco> importarCsv(MultipartFile arquivo);
}
