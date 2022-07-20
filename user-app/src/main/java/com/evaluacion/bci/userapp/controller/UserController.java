package com.evaluacion.bci.userapp.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${pass.patron}")
    String passPattern;

    @Value("${email.patron}")
    String emailPattern;


    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) throws ErrorRespuestaHandler{

        ResponseEntity<User> response;

        if (newUser == null){
            throw new ErrorRespuestaHandler("No hay contenido en la solicitud", HttpStatus.NO_CONTENT.value());
        }

        if (!isEmailValid(newUser.getEmail())){
            throw new ErrorRespuestaHandler("Correo no cumple con formato", HttpStatus.NOT_ACCEPTABLE.value());
        }

        if (!isPasswordValid(newUser.getPassword())){
            throw new ErrorRespuestaHandler("Password no cumple con formato establecido", HttpStatus.NOT_ACCEPTABLE.value());
        }
        newUser = userService.addUser(newUser);
        if (newUser !=null){

            response = new ResponseEntity<User>(newUser, HttpStatus.OK);
        }
        else {
            throw new ErrorRespuestaHandler("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> RetrieveUser(@PathVariable String id) throws ErrorRespuestaHandler{
        
        ResponseEntity<User> response;
        if (id==null){
            throw new ErrorRespuestaHandler("Error en Metodo GET", HttpStatus.METHOD_NOT_ALLOWED.value());
        }
        User user = userService.RetrieveUser(id);
        if(user != null){
            response = new ResponseEntity<User>(user, HttpStatus.OK);
            return response;
        }else{
            throw new ErrorRespuestaHandler("Usuario no existe", HttpStatus.BAD_REQUEST.value());
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable String id, @RequestBody User user) throws ErrorRespuestaHandler{

        ResponseEntity<User> response;

        user = userService.UpdateUser(id, user);
        if (user != null){
            response = new ResponseEntity<User>(user, HttpStatus.OK);
            return response;
        }else{
            throw new ErrorRespuestaHandler("Error al actualizar usuario con id: " + id, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @DeleteMapping("/users/{id}")
    public ErrorRespuesta DeleteUser(@PathVariable String id) throws ErrorRespuestaHandler{
        ErrorRespuesta respuesta = new ErrorRespuesta();
        
        if (userService.DeleteUser(id)){
            respuesta.setCodigo(HttpStatus.OK.value());
            respuesta.setMensaje("Usuario eliminado");
        }else{
            throw new ErrorRespuestaHandler("Error al eliminar usuario con id: " + id, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return respuesta;
    }
    
    /**
    * Check password with Regular exp pass.patron
    * @param String password must not be {@literal null}.
    * @return boolean
    */
    public boolean isPasswordValid(String password) {
        System.out.println("passPattern: " + passPattern);
        return password.matches(passPattern);
    }

    /**
    * Valida si email cumple con formato aaaaaaaa@dominio.cl
    * @param String must not be {@literal null}.
    * @return boolean
    */
    public boolean isEmailValid(String email) {
        System.out.println("emailPattern: " + emailPattern);

        Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
        
        //return email.matches(emailPattern);
    }

    @ExceptionHandler(ErrorRespuestaHandler.class)
    public ResponseEntity<ErrorRespuesta> exceptionHandler(ErrorRespuestaHandler ex) {
        ErrorRespuesta error = new ErrorRespuesta();
        error.setCodigo(ex.getCodigo());
        error.setMensaje(ex.getMensaje());
        return new ResponseEntity<ErrorRespuesta>(error, HttpStatus.OK);
    }
}
