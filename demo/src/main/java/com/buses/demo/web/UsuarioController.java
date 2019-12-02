package com.buses.demo.web;

import com.buses.demo.domain.Usuario;
import com.buses.demo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            List<Resource<Usuario>> usuarios = usuarioService.getAllUsuarios().stream()
                    .map(usuario -> new Resource<Usuario>(usuario,
                            linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()));
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a User with an Id", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> getUsuarioById(@PathVariable Long id){
        try {
//            Usuario usuario = usuarioService.getUsuarioById(id).orElseThrow(() -> new RuntimeException("Could not find usuario " + id));
            if (usuarioService.existUsuarioById(id)){
                Usuario user = usuarioService.getUsuarioById(id);
                return ResponseEntity.ok(new Resource<Usuario>(user,
                    linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search for buses that match an example", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByExample(@RequestBody Usuario user){
        try {
//            return new ResponseEntity(usuarioService.findUsuariosByExample(user), HttpStatus.OK);
            List<Resource<Usuario>> usuarios = usuarioService.findUsuariosByExample(user).stream()
                    .map(usuario -> new Resource<Usuario>(usuario,
                            linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
                    linkTo(methodOn(UsuarioController.class).getUsuariosByExample(user)).withSelfRel()));
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search for buses that match an example", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<Resources<Resource<Usuario>>> getUsuariosByName(@RequestParam(value = "name", required = false, defaultValue = "name") String name){
        try {
            List<Resource<Usuario>> usuarios = usuarioService.findUsuariosByName(name).stream()
                    .map(usuario -> new Resource<Usuario>(usuario,
                            linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new Resources<Resource<Usuario>>(usuarios,
                    linkTo(methodOn(UsuarioController.class).getUsuariosByName(name)).withSelfRel()));
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Add new Usuario", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Usuario>> addNewUsuario(@RequestBody Usuario newUsuario){
        try {
            Usuario usuario = usuarioService.addNewUsuario(newUsuario);
            return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId()))
                    .toUri()).body(new Resource<>(usuario,
                            linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
//            return new ResponseEntity(usuarioService.addNewUsuario(newUsuario), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a Usuario with an ID", response = Usuario.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Resource<Usuario>> updateUsuarioById(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            if (usuarioService.existUsuarioById(id)){
                Usuario user = usuarioService.updateUsuarioById(id, usuario);
                return ResponseEntity.ok(new Resource<>(user,
                        linkTo(methodOn(UsuarioController.class).getUsuarioById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios")));
            }
//                return new ResponseEntity(usuarioService.updateUsuarioById(id, usuario), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a Usuario with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
        try {
            if (usuarioService.existUsuarioById(id)){
                usuarioService.deleteUsuarioById(id);
                return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
