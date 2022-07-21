package com.evaluacion.bci.userapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.evaluacion.bci.userapp.entity.PhoneDao;
import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.model.PhoneDTO;
import com.evaluacion.bci.userapp.model.UserRespuestaDTO;

@Component
public class UserUtil {
    
    @Value("${pass.patron}")
    String passPattern;

    @Value("${email.patron}")
    String emailPattern;

    /**
     * Return the mapping from Object PhoneDao to Phone
     * @param UserDao must not be {@literal null}.
     * @param List<PhoneDao> must not be {@literal null}.
	 * @return a List of Phone Objects
     * */
    public List<PhoneDao> PhoneDaoMapping(UserDao user, List<PhoneDTO> phones){
        PhoneDao phone;
        List<PhoneDao> phonesDao = new ArrayList<PhoneDao>();

        for(PhoneDTO p: phones){
            phone = new PhoneDao(p.getId(), p.getNumber(), p.getCitycode(), p.getCountrycode(), user);
            //System.out.println("TELEFONO: " + phone.toString());
            phonesDao.add(phone);
        }

        return phonesDao;
    }

    /**
     * Return the mapping from Object PhoneDao to Phone
     * @param List<PhoneDao> must not be {@literal null}.
	 * @return a List of Phone Objects
     * */
    public List<PhoneDTO> PhoneMapping(List<PhoneDao> phonesDao){
        PhoneDTO phone;
        List<PhoneDTO> phones = new ArrayList<PhoneDTO>();

        for(PhoneDao p: phonesDao){
            phone = new PhoneDTO(p.getId(), p.getNumber(), p.getCitycode(), p.getCountrycode());
            phones.add(phone);
        }

        return phones;
    }

    /**
     * Return the mapping from Object UserDao to User
     * @param UserDao must not be {@literal null}.
	 * @return a User Object
     * */
    public UserRespuestaDTO UserMapping(UserDao userDao){

        return new UserRespuestaDTO(userDao.getId(), userDao.getName(), userDao.getEmail(), userDao.getPassword(), PhoneMapping(userDao.getPhones()), userDao.getCreated(), userDao.getModified(), userDao.getLast_login(), userDao.Isactive());
    }

 
    /**
    * Check password with Regular exp pass.patron
    * @param String password must not be {@literal null}.
    * @return boolean
    */
    public boolean isPasswordValid(String password) {
        //System.out.println("passPattern: " + passPattern);
        return password.matches(passPattern);
    }

    /**
    * Valida si email cumple con formato aaaaaaaa@dominio.cl
    * @param String must not be {@literal null}.
    * @return boolean
    */
    public boolean isEmailValid(String email) {
        //System.out.println("emailPattern: " + emailPattern);

        Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
        
        //return email.matches(emailPattern);
    }

}
