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
import com.evaluacion.bci.userapp.model.ErrorRespuestaDTO;
import com.evaluacion.bci.userapp.model.UserDTO;
import com.evaluacion.bci.userapp.model.UserRespuestaDTO;
import com.evaluacion.bci.userapp.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserDTO newUser) throws ErrorRespuestaHandler{

        String token = "";

        UserRespuestaDTO user = userService.addUser(newUser,token);
        
                
        return ResponseEntity.ok(user);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> RetrieveUser(@PathVariable String id) throws ErrorRespuestaHandler{
        
        UserRespuestaDTO user = userService.RetrieveUser(id);

        return ResponseEntity.ok(user);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable String id, @RequestBody UserDTO user) throws ErrorRespuestaHandler{

        UserRespuestaDTO newUser = userService.UpdateUser(id, user);
        return ResponseEntity.ok(newUser);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable String id) throws ErrorRespuestaHandler{
        
        if (userService.DeleteUser(id)){
            return ResponseEntity.ok(new ErrorRespuestaDTO(HttpStatus.OK.value(),"Usuario eliminado"));
        }else {
            return ResponseEntity.ok(new ErrorRespuestaDTO(HttpStatus.OK.value(),"Usuario no existe"));
        }
        
    }
    

    @ExceptionHandler(ErrorRespuestaHandler.class)
    public ResponseEntity<ErrorRespuestaDTO> exceptionHandler(ErrorRespuestaHandler ex) {
        ErrorRespuestaDTO error = new ErrorRespuestaDTO(ex.getCodigo(), ex.getMensaje());

        return new ResponseEntity<ErrorRespuestaDTO>(error, HttpStatus.OK);
    }
}
