package com.evaluacion.bci.userapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.evaluacion.bci.userapp.entity.PhoneDao;
import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.exception.ErrorRespuestaHandler;
import com.evaluacion.bci.userapp.model.User;
import com.evaluacion.bci.userapp.repository.PhoneRepository;
import com.evaluacion.bci.userapp.repository.UserRepository;
import com.evaluacion.bci.userapp.util.UserUtil;

@Component
public class UserService {

    @Autowired
    private UserRepository uRepo;
    @Autowired
    private PhoneRepository pRepo;

    @Autowired
    private UserUtil userUtil;
    
    UserDao user;
    
    List<PhoneDao> phonesDao = new ArrayList<PhoneDao>();

    public User addUser(User newUser, String token) throws ErrorRespuestaHandler{

        if (newUser == null){
            throw new ErrorRespuestaHandler("No hay contenido en la solicitud", HttpStatus.NO_CONTENT.value());
        }

        if (!userUtil.isEmailValid(newUser.getEmail())){
            throw new ErrorRespuestaHandler("Correo no cumple con formato", HttpStatus.NOT_ACCEPTABLE.value());
        }

        if (!userUtil.isPasswordValid(newUser.getPassword())){
            throw new ErrorRespuestaHandler("Password no cumple con formato establecido", HttpStatus.NOT_ACCEPTABLE.value());
        }

        try {
            user = uRepo.save(new UserDao(newUser.getName(), newUser.getEmail(), newUser.getPassword(), token));

            phonesDao = userUtil.PhoneDaoMapping(user, newUser.getPhones());
    
            pRepo.saveAll(phonesDao);
            user.setPhones(phonesDao);

            return userUtil.UserMapping(user);

        } catch (Exception ie){
            throw new ErrorRespuestaHandler("Error al crear usuario " + ie.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public UserDao getUserDetails(String id){

        try {
            user = uRepo.getReferenceById(id);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public UserDao loadUserByUserAndPass(String name, String password) throws ErrorRespuestaHandler{

        try {
            user = uRepo.getReferenceByNameAndPassword(name, password);
            return user;
        } catch (Exception e) {
            throw new ErrorRespuestaHandler("Error en credenciales", HttpStatus.FORBIDDEN.value());
        }
    }

    public User RetrieveUser(String id) throws ErrorRespuestaHandler{
        
        if (id==null){
            throw new ErrorRespuestaHandler("Error en Metodo GET", HttpStatus.METHOD_NOT_ALLOWED.value());
        }
        
        try{

            user = uRepo.getReferenceById(id);
            return userUtil.UserMapping(user);

        }catch (Exception e){
            throw new ErrorRespuestaHandler("Error al obtener usuario" + e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
    
    public User UpdateUser(String id, User user) throws ErrorRespuestaHandler{
        
        UserDao userDao;
        List<PhoneDao> phonesDao;

        if(uRepo.existsById(id))
        {
            try{
                userDao = uRepo.getReferenceById(id);
                //phonesDao = userDao.getPhones();

                phonesDao = userUtil.PhoneDaoMapping(userDao, user.getPhones());
                // Actualiza los datos de User
                userDao.setName(user.getName());
                userDao.setEmail(user.getEmail());
                userDao.setPassword(user.getPassword());
                userDao.setPhones(phonesDao);
                userDao.setModified(new Date(System.currentTimeMillis()));
                userDao.setIsactive(user.getIsactive());

                pRepo.saveAll(phonesDao);
                uRepo.save(userDao);

                return userUtil.UserMapping(userDao);  

            }catch (Exception e){
                throw new ErrorRespuestaHandler("Error al actualizar usuario " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            
        }
        throw new ErrorRespuestaHandler("Error al obtener usuario con id: " + id, HttpStatus.BAD_REQUEST.value());
    }

    public boolean DeleteUser(String id) throws ErrorRespuestaHandler{
        try{

            if (uRepo.existsById(id)) {
                UserDao user = uRepo.getReferenceById(id);
                user.setIsactive(false);
                user.setModified(new Date(System.currentTimeMillis()));

                uRepo.save(user);
                return true;
            } else {

                throw new ErrorRespuestaHandler("Usuario no existe", HttpStatus.BAD_REQUEST.value());
            }
        }catch (Exception e){
            throw new ErrorRespuestaHandler("Error al eliminar usuario " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
