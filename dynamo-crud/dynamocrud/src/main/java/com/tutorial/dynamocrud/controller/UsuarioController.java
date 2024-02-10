package com.tutorial.dynamocrud.controller;

import com.tutorial.dynamocrud.dto.UsuarioDto;
import com.tutorial.dynamocrud.entity.Usuario;
import com.tutorial.dynamocrud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<Iterable<Usuario>> list(){
        return ResponseEntity.ok(usuarioService.lista());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Usuario> getOne(@PathVariable("usuarioId") String usuarioId){
        if(!usuarioService.existsId(usuarioId))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(usuarioService.getOne(usuarioId));
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody UsuarioDto usuarioDto){
        if(usuarioService.existsId(usuarioDto.getId()))
            return new ResponseEntity("el id ya existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsNombre(usuarioDto.getNombre()))
            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
        try{
            return ResponseEntity.ok(usuarioService.save(usuarioDto));
        }catch(Exception e){
            System.err.println("PASA ESTO ERRROR: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> update(@RequestBody UsuarioDto usuarioDto){
        if(!usuarioService.existsId(usuarioDto.getId()))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        if(usuarioService.existsNombre(usuarioDto.getNombre()) && !usuarioService.getOne(usuarioDto.getId()).getNombre().equals(usuarioDto.getNombre()))
            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuarioService.update(usuarioDto));
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> delete(@PathVariable("usuarioId") String usuarioId){
        if(!usuarioService.existsId(usuarioId))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        usuarioService.delete(usuarioId);
        return new ResponseEntity("usuario eliminado", HttpStatus.OK);
    }




}
