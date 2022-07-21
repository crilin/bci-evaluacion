package com.evaluacion.bci.userapp.service;

import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.exception.ErrorRespuestaHandler;
import com.evaluacion.bci.userapp.model.UserDTO;
import com.evaluacion.bci.userapp.model.UserRespuestaDTO;

public interface IUserService {
    
    public UserRespuestaDTO addUser(UserDTO newUser, String token) throws ErrorRespuestaHandler;

    public UserDao getUserDetails(String id) throws ErrorRespuestaHandler;

    public UserDao loadUserByUserAndPass(String name, String password) throws ErrorRespuestaHandler;

    public UserRespuestaDTO RetrieveUser(String id) throws ErrorRespuestaHandler;

    public UserRespuestaDTO UpdateUser(String id, UserDTO user) throws ErrorRespuestaHandler;

    public boolean DeleteUser(String id) throws ErrorRespuestaHandler;
}
