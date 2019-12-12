package com.buses.demo.web;

import com.buses.demo.config.ApiPageable;
import com.buses.demo.domain.Usuario;
import com.buses.demo.exception.RecordNotFoundException;
import com.buses.demo.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return usuarioService.getAllUsuarios().stream()
            .map(usuario -> new Resource<>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.collectingAndThen(Collectors.toList(),
                resources -> ResponseEntity.ok(new Resources<>(resources,
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()))));
    }

    @ApiPageable
    @ApiOperation(value = "Page all Usuarios", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/pages", produces = { "application/hal+json" })
    public ResponseEntity<PagedResources<Usuario>> getPageAllUsuarios(
            @ApiIgnore(
                    "Ignored because swagger ui shows the wrong params, " +
                            "instead they are explained in the implicit params"
            ) @PageableDefault(page = 0, size = 5)Pageable pageable, PagedResourcesAssembler<Usuario> paged){
//            PageRequest.of(1, 2, Sort.by("nombre").descending()/*.and(Sort.by("name"))*/)
        Page < Usuario > page = usuarioService.getAllUsuarios(pageable);
        PagedResources pr = paged.toResource(page, usuario -> new Resource(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
        return ResponseEntity.ok(pr);
    }

    @ApiOperation(value = "Search a User with an Id", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> getUsuarioById(@PathVariable Long id){
        return usuarioService.getUsuarioById(id)
            .map(usuario -> new Resource<>(usuario,
                    linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RecordNotFoundException("Could not find usuario: " + id));
    }

    @ApiOperation(value = "Search for buses that match an example", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByExample(@RequestBody Usuario user){
        return usuarioService.findUsuariosByExample(user).stream()
            .map(usuario -> new Resource<>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.collectingAndThen(Collectors.toList(), usuarios -> ResponseEntity.ok(
                new Resources<>(usuarios,
                linkTo(methodOn(UsuarioController.class).getUsuariosByExample(user)).withSelfRel()))));
    }

    @ApiOperation(value = "Search usuarios by name", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByName(
            @RequestParam(value = "name", required = false, defaultValue = "name") String name){
        return usuarioService.findUsuariosByName(name).stream()
            .map(usuario -> new Resource<>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
            .collect(Collectors.collectingAndThen(Collectors.toList(),
                usuarios -> ResponseEntity.ok(new Resources<>(usuarios,
                linkTo(methodOn(UsuarioController.class).getUsuariosByName(name)).withSelfRel()))));
    }

    @ApiOperation(value = "Add new Usuario", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Usuario>> addNewUsuario(@Valid @RequestBody Usuario newUsuario){
        return Stream.of(usuarioService.addNewUsuario(newUsuario))
            .map(usuario -> ResponseEntity.created(
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).toUri())
                .body(new Resource<>(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios"))))
            .findFirst().get();
    }

    @ApiOperation(value = "Update a Usuario with an ID", response = Usuario.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> updateUsuarioById(
            @PathVariable Long id, @RequestBody Usuario usuario){
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
