package com.evaluacion.bci.userapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.exception.ErrorRespuestaHandler;

@Service
public class JwtUserDetailsService {
    
    @Autowired
    UserService userService;

    public UserDao loadUserByUsername (String name, String password) throws ErrorRespuestaHandler {

        UserDao user = userService.getUserDetails(name, password);
        if(user != null){
            return user;
        }else{
            throw new ErrorRespuestaHandler("usuario no encontrado", HttpStatus.FORBIDDEN.value());
        }
    }
}
