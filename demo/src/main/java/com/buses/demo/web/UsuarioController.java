package com.buses.demo.web;

import com.buses.demo.domain.Usuario;
import com.buses.demo.exception.RecordNotFoundException;
import com.buses.demo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
@Api(value="onlinestore", description="Operations")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Search all Usuarios", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, produces = { "application/hal+json" })
    public ResponseEntity<Resources<Resource<Usuario>>> getAllUsuarios(){
        List<Resource<Usuario>> usuarios = usuarioService.getAllUsuarios().stream()
            .map(usuario -> new Resource<Usuario>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()));
    }

    @ApiOperation(value = "Page all Usuarios", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/pages", produces = { "application/hal+json" })
    public ResponseEntity<Page<Usuario>> getPageAllUsuarios(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pagina){
        Page<Usuario> page = usuarioService.getAllUsuarios(
            PageRequest.of(pagina, 2, Sort.by("nombre").descending()/*.and(Sort.by("name"))*/));
        return ResponseEntity.ok(page);
    }

    @ApiOperation(value = "Search a User with an Id", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> getUsuarioById(@PathVariable Long id){
        Usuario user = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(new Resource<Usuario>(user,
            linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
    }

    @ApiOperation(value = "Search for buses that match an example", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByExample(@RequestBody Usuario user){
        List<Resource<Usuario>> usuarios = usuarioService.findUsuariosByExample(user).stream()
            .map(usuario -> new Resource<Usuario>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
                linkTo(methodOn(UsuarioController.class).getUsuariosByExample(user)).withSelfRel()));
    }

    @ApiOperation(value = "Search usuarios by name", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByName(
            @RequestParam(value = "name", required = false, defaultValue = "name") String name){
        List<Resource<Usuario>> usuarios = usuarioService.findUsuariosByName(name).stream()
            .map(usuario -> new Resource<Usuario>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.toList());

        return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
            linkTo(methodOn(UsuarioController.class).getUsuariosByName(name)).withSelfRel()));
    }

    @ApiOperation(value = "Add new Usuario", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Usuario>> addNewUsuario(@Valid @RequestBody Usuario newUsuario){
        Usuario usuario = usuarioService.addNewUsuario(newUsuario);
        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId()))
            .toUri()).body(new Resource<>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
    }

    @ApiOperation(value = "Update a Usuario with an ID", response = Usuario.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> updateUsuarioById(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario user = usuarioService.updateUsuarioById(id, usuario);
        return ResponseEntity.ok(new Resource<>(user,
            linkTo(methodOn(UsuarioController.class).getUsuarioById(user.getId())).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
    }

    @ApiOperation(value = "Delete a Usuario with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
        if (!usuarioService.deleteUsuarioById(id))
            throw new RecordNotFoundException("Could not find usuario: " + id);
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}
