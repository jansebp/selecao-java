package com.indra.api.converter;

import com.indra.api.dto.HistoricoPrecoPersist;
import com.indra.api.entity.HistoricoPreco;
import org.springframework.core.convert.converter.Converter;

public final class HistoricoPrecoConverter {

    public static class PersistToEntity implements Converter<HistoricoPrecoPersist, HistoricoPreco> {

        private PersistToEntity() {
        }

        public static PersistToEntity criar() {
            return new PersistToEntity();
        }

        @Override
        public HistoricoPreco convert(HistoricoPrecoPersist historicoPrecoPersist) {
            return new HistoricoPreco(null, historicoPrecoPersist.getDataColeta(),
                    historicoPrecoPersist.getRevendedora(), historicoPrecoPersist.getCnpj(),
                    historicoPrecoPersist.getMunicipio(), historicoPrecoPersist.getSiglaEstado(),
                    historicoPrecoPersist.getSiglaRegiao(), historicoPrecoPersist.getProduto(),
                    historicoPrecoPersist.getBandeira(), historicoPrecoPersist.getValorVenda(),
                    historicoPrecoPersist.getValorCompra(), historicoPrecoPersist.getUnidadeMedida());
        }
    }
}
