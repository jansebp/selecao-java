package com.indra.api.service;

import com.indra.api.converter.HistoricoPrecoConverter;
import com.indra.api.dto.HistoricoPrecoPersist;
import com.indra.api.dto.ValorMedioResponse;
import com.indra.api.entity.HistoricoPreco;
import com.indra.api.entity.Usuario;
import com.indra.api.repository.HistoricoPrecoRepository;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class HistoricoPrecoServiceImpl implements HistoricoPrecoService {

    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;


    @Override
    public HistoricoPreco cadastrar(HistoricoPrecoPersist historicoPrecoPersist) {
        return historicoPrecoRepository.save(Objects.requireNonNull(HistoricoPrecoConverter.PersistToEntity.criar().convert(historicoPrecoPersist)));
    }

    @Override
    public HistoricoPreco buscar(Long id) {
        Optional<HistoricoPreco> obj = historicoPrecoRepository.findById(id);
        return obj.get();
    }

    @Override
    public HistoricoPreco atualizar(Long id, HistoricoPrecoPersist historicoPrecoPersist) {
        final HistoricoPreco[] response = {new HistoricoPreco()};
        historicoPrecoRepository.findById(id).map(h -> {
            h.setDataColeta(historicoPrecoPersist.getDataColeta());
            h.setUnidadeMedida(historicoPrecoPersist.getUnidadeMedida());
            h.setBandeira(historicoPrecoPersist.getBandeira());
            h.setCnpj(historicoPrecoPersist.getCnpj());
            h.setMunicipio(historicoPrecoPersist.getMunicipio());
            h.setProduto(historicoPrecoPersist.getProduto());
            h.setRevendedora(historicoPrecoPersist.getRevendedora());
            h.setSiglaEstado(historicoPrecoPersist.getSiglaEstado());
            h.setSiglaRegiao(historicoPrecoPersist.getSiglaRegiao());
            h.setValorCompra(historicoPrecoPersist.getValorCompra());
            h.setValorVenda(historicoPrecoPersist.getValorVenda());
            response[0] = historicoPrecoRepository.save(h);
            return response[0];
        }).orElseThrow(NoSuchElementException::new);

        return response[0];
    }

    @Override
    public void deletar(Long id) {
        Optional<HistoricoPreco> obj = historicoPrecoRepository.findById(id);
        obj.orElseThrow(() -> new RuntimeException("Não há registro com o ID informado."));
        historicoPrecoRepository.deleteById(id);
    }

    @Override
    public Page<HistoricoPreco> listarPorRegiao(String siglaRegiao, Pageable pageable) {
        if (!historicoPrecoRepository.existsBySiglaRegiaoIgnoreCase(siglaRegiao)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + siglaRegiao);
        }
        return historicoPrecoRepository.findAllBySiglaRegiaoIgnoreCase(siglaRegiao, pageable);
    }

    @Override
    public Page<HistoricoPreco> listarPorBandeira(String bandeira, Pageable pageable) {
        if (historicoPrecoRepository.existsByBandeiraIgnoreCase(bandeira)) {
            return historicoPrecoRepository.findAllByBandeiraIgnoreCase(bandeira, pageable);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + bandeira);
    }

    @Override
    public Page<HistoricoPreco> listarPorRevendedora(String revendedora, Pageable pageable) {
        if (historicoPrecoRepository.existsByRevendedoraIgnoreCase(revendedora)) {
            return historicoPrecoRepository.findAllByRevendedoraIgnoreCase(revendedora, pageable);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + revendedora);
    }

    @Override
    public Page<HistoricoPreco> listarPorData(LocalDate dataColeta, Pageable pageable) {
        if (!historicoPrecoRepository.existsByDataColeta(dataColeta)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + dataColeta);
        }
        return historicoPrecoRepository.findAllByDataColeta(dataColeta, pageable);
    }

    @Override
    public Page<HistoricoPreco> listar(Pageable pageable) {
        return historicoPrecoRepository.findAll(pageable);
    }

    @Override
    public ValorMedioResponse getMediaPrecosByMunicipio(String municipio) {
        if (!historicoPrecoRepository.existsByMunicipioIgnoreCase(municipio)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + municipio);
        }
        return historicoPrecoRepository.getMediaPrecosByMunicipio(municipio);
    }

    @Override
    public ValorMedioResponse getMediaPrecosByBandeira(String bandeira) {
        if (!historicoPrecoRepository.existsByBandeiraIgnoreCase(bandeira)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro para o valor: " + bandeira);
        }
        return historicoPrecoRepository.getMediaPrecosByBandeira(bandeira);
    }

    @Override
    public List<HistoricoPreco> importarCsv(MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "O arquivo está vazio");
        }
        try {
            Reader reader = new InputStreamReader(arquivo.getInputStream(), StandardCharsets.UTF_16);

            BeanListProcessor<HistoricoPreco> rowProcessor = new BeanListProcessor<>(HistoricoPreco.class);
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            settings.getFormat().setDelimiter("\t");
//            settings.setNumberOfRowsToSkip(1);
            settings.setProcessor(rowProcessor);

            CsvParser parser = new CsvParser(settings);

            parser.parse(reader);
            List<HistoricoPreco> historicos = rowProcessor.getBeans();

            for (HistoricoPreco h : historicos) {
                if (h.getValorCompra() == null) {
                    h.setValorCompra(0.0);
                }
                if (h.getValorVenda() == null) {
                    h.setValorVenda(0.0);
                }
            }

            historicos.stream().parallel().forEach(historico -> historicoPrecoRepository.save(historico));

            return historicos;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível carregar o arquivo.");
        }
    }
}
