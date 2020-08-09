package com.indra.api.service;

import com.indra.api.converter.UsuarioConverter;
import com.indra.api.dto.UsuarioPersist;
import com.indra.api.entity.Usuario;
import com.indra.api.repository.UsuarioRepository;
import com.indra.api.utils.Transacional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transacional
    @Override
    public Usuario cadastrar(UsuarioPersist usuarioPersist) {
        return usuarioRepository.save(Objects.requireNonNull(UsuarioConverter.PersistToEntity.criar().convert(usuarioPersist)));
    }

    @Override
    public Usuario buscar(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.get();
    }

    @Override
    public Usuario atualizar(Long id, UsuarioPersist usuario) {
        final Usuario[] response = {new Usuario()};
        usuarioRepository.findById(id).map(u -> {
            u.setNome(usuario.getNome());
            u.setLogin(usuario.getLogin());
            response[0] = usuarioRepository.save(u);
            return response[0];
        }).orElseThrow(NoSuchElementException::new);

        return response[0];
    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        obj.orElseThrow(() -> new RuntimeException("Não há registro com o ID informado."));
        usuarioRepository.deleteById(id);
    }

    @Override
    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
