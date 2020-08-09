package com.indra.api.service;

import com.indra.api.dto.UsuarioPersist;
import com.indra.api.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Usuario cadastrar(UsuarioPersist usuarioPersist);

    Usuario buscar(Long id);

    Usuario atualizar(Long id, UsuarioPersist usuarioPersist);

    void deletar(Long id);

    Page<Usuario> listar(Pageable pageable);
}
