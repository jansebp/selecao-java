package com.indra.api.repository;

import com.indra.api.dto.ValorMedioResponse;
import com.indra.api.entity.HistoricoPreco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco, Long> {

    @Query(value = "SELECT new com.indra.api.dto.ValorMedioResponse(AVG(HP.valorVenda), AVG(HP.valorCompra), (AVG(HP.valorVenda) + AVG(HP.valorCompra))/2.0)" +
            " FROM HistoricoPreco AS HP" +
            " WHERE UPPER(HP.municipio) = UPPER(:municipio)")
    ValorMedioResponse getMediaPrecosByMunicipio(@Param("municipio") String municipio);

    @Query(value = "SELECT new com.indra.api.dto.ValorMedioResponse(AVG(HP.valorVenda), AVG(HP.valorCompra), (AVG(HP.valorVenda) + AVG(HP.valorCompra))/2.0)" +
            " FROM HistoricoPreco AS HP" +
            " WHERE UPPER(HP.bandeira) = UPPER(:bandeira)")
    ValorMedioResponse getMediaPrecosByBandeira(@Param("bandeira") String bandeira);

    Page<HistoricoPreco> findAllBySiglaRegiaoIgnoreCase(String siglaRegiao, Pageable pageable);

    Page<HistoricoPreco> findAllByBandeiraIgnoreCase(String bandeira, Pageable pageable);

    Page<HistoricoPreco> findAllByRevendedoraIgnoreCase(String revendedora, Pageable pageable);

    Page<HistoricoPreco> findAllByDataColeta(LocalDate dataColeta, Pageable pageable);

    boolean existsByMunicipioIgnoreCase(String municipio);

    boolean existsBySiglaRegiaoIgnoreCase(String siglaRegiao);

    boolean existsByBandeiraIgnoreCase(String bandeira);

    boolean existsByRevendedoraIgnoreCase(String revendedora);

    boolean existsByDataColeta(LocalDate dataColeta);
}
