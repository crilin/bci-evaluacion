package com.evaluacion.bci.userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.bci.userapp.exception.ErrorRespuestaHandler;
import com.evaluacion.bci.userapp.model.ErrorRespuesta;
import com.evaluacion.bci.userapp.model.User;
import com.evaluacion.bci.userapp.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User newUser) throws ErrorRespuestaHandler{

        String token = "";

        newUser = userService.addUser(newUser,token);

        return ResponseEntity.ok(newUser);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> RetrieveUser(@PathVariable String id) throws ErrorRespuestaHandler{
        
        User user = userService.RetrieveUser(id);

        return ResponseEntity.ok(user);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable String id, @RequestBody User user) throws ErrorRespuestaHandler{

        user = userService.UpdateUser(id, user);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable String id) throws ErrorRespuestaHandler{
        ErrorRespuesta respuesta = new ErrorRespuesta();
        
        if (userService.DeleteUser(id)){
            respuesta.setCodigo(HttpStatus.OK.value());
            respuesta.setMensaje("Usuario eliminado");
            
        }
        return ResponseEntity.ok(respuesta);
    }
    

    @ExceptionHandler(ErrorRespuestaHandler.class)
    public ResponseEntity<ErrorRespuesta> exceptionHandler(ErrorRespuestaHandler ex) {
        ErrorRespuesta error = new ErrorRespuesta();
        error.setCodigo(ex.getCodigo());
        error.setMensaje(ex.getMensaje());
        return new ResponseEntity<ErrorRespuesta>(error, HttpStatus.OK);
    }
}
