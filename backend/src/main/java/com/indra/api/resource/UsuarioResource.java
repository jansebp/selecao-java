package com.indra.api.resource;

import com.indra.api.dto.UsuarioPersist;
import com.indra.api.entity.Usuario;
import com.indra.api.service.UsuarioService;
import com.indra.api.utils.ConstantesPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = ConstantesPaths.PATH_USUARIO, produces = MediaType.APPLICATION_JSON_VALUE, tags = "Usuario")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = ConstantesPaths.PATH_USUARIO, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ApiOperation(value = "Cadastrar usuário", notes = "Este recurso permite cadastrar um usuário na base de dados.", response = Usuario.class)
    public Usuario cadastrarUsuario(@RequestBody UsuarioPersist usuarioPersist) {
        return usuarioService.cadastrar(usuarioPersist);
    }

    @GetMapping(path = {"/{id}"})
    @ApiOperation(value = "Buscar usuário", notes = "Este recurso permite buscar um usuário na base de dados.", response = Usuario.class)
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar usuário", notes = "Este recurso permite atualizar um usuário na base de dados.", response = Usuario.class)
    public Usuario atualizarUsuario(@Valid @RequestBody UsuarioPersist persist, @PathVariable Long id) {
        return usuarioService.atualizar(id, persist);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar usuário", notes = "Este recurso permite deletar um usuário da base de dados.")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Listar usuários", notes = "Este recurso permite listar usuários")
    public Page<Usuario> listarUsuarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());

        return usuarioService.listar(pageable);
    }
}
