package com.indra.api.converter;

import com.indra.api.dto.UsuarioPersist;
import com.indra.api.entity.Usuario;
import org.springframework.core.convert.converter.Converter;

public final class UsuarioConverter {

    public static class PersistToEntity implements Converter<UsuarioPersist, Usuario> {

        private PersistToEntity() {
        }

        public static PersistToEntity criar() {
            return new PersistToEntity();
        }

        @Override
        public Usuario convert(UsuarioPersist usuarioPersist) {
            return new Usuario(null, usuarioPersist.getNome(), usuarioPersist.getLogin());
        }
    }
}
