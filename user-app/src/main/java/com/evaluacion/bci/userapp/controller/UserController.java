package com.evaluacion.bci.userapp.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.bci.userapp.entity.PhoneDao;
import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.model.Phone;
import com.evaluacion.bci.userapp.model.User;
import com.evaluacion.bci.userapp.service.PhoneRepository;
import com.evaluacion.bci.userapp.service.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private PhoneRepository pRepo;
    
    @PostMapping("/users")
    public User addUser(@RequestBody User newUser){

        System.out.println("User: " + newUser.toString());
        List<PhoneDao> phonesDao = new ArrayList<PhoneDao>();

        UserDao user = uRepo.save(new UserDao(newUser.getName(), newUser.getEmail(), newUser.getPassword()));

        phonesDao = PhoneDaoMapping(user, newUser.getPhones());

        pRepo.saveAll(phonesDao);
        user.setPhones(phonesDao);

        return UserMapping(user);
    }

    @GetMapping("/users/{id}")
    public User RetrieveUser(@PathVariable String id){
        UserDao user;
        try{
            user = uRepo.getReferenceById(id);
            
        }catch (EntityNotFoundException efe){
            return null;
        }
        
        return UserMapping(user);

    }

    @PutMapping("/users/{id}")
    public User UpdateUser(@PathVariable String id, @RequestBody User user){

        UserDao userDao;
        List<PhoneDao> phonesDao;

        if(uRepo.existsById(id))
        {
            try{
                userDao = uRepo.getReferenceById(id);
                //phonesDao = userDao.getPhones();

                phonesDao = PhoneDaoMapping(userDao, user.getPhones());
                // Actualiza los datos de User
                userDao.setName(user.getName());
                userDao.setEmail(user.getEmail());
                userDao.setPassword(user.getPassword());
                userDao.setPhones(phonesDao);
                userDao.setModified(new Date(System.currentTimeMillis()));
                userDao.setIsactive(user.getIsactive());

                pRepo.saveAll(phonesDao);
                uRepo.save(userDao);
//                

            }catch (EntityNotFoundException efe){
                return null;
            }

            return UserMapping(userDao);
        }
        
        return null;
    }

    /**
     * Return the mapping from Object UserDao to User
     * @param UserDao must not be {@literal null}.
	 * @return a User Object
     * */
    private User UserMapping(UserDao userDao){

        return new User(userDao.getId(), userDao.getName(), userDao.getEmail(), userDao.getPassword(), PhoneMapping(userDao.getPhones()), userDao.getCreated(), userDao.getModified(), userDao.getLast_login(), userDao.Isactive());
    }

    /**
     * Return the mapping from Object PhoneDao to Phone
     * @param List<PhoneDao> must not be {@literal null}.
	 * @return a List of Phone Objects
     * */
    private List<Phone> PhoneMapping(List<PhoneDao> phonesDao){
        Phone phone;
        List<Phone> phones = new ArrayList<Phone>();

        for(PhoneDao p: phonesDao){
            phone = new Phone(p.getId(), p.getNumber(), p.getCitycode(), p.getCountrycode());
            phones.add(phone);
        }

        return phones;
    }

    /**
     * Return the mapping from Object UserDao to User
     * @param User must not be {@literal null}.
	 * @return a UserDao Object
     * */
    private UserDao UserDaoMapping(User user){
 
        return uRepo.getReferenceById(user.getId());
    }

    /**
     * Return the mapping from Object PhoneDao to Phone
     * @param List<PhoneDao> must not be {@literal null}.
	 * @return a List of Phone Objects
     * */
    private List<PhoneDao> PhoneDaoMapping(UserDao user, List<Phone> phones){
        PhoneDao phone;
        List<PhoneDao> phonesDao = new ArrayList<PhoneDao>();

        for(Phone p: phones){
            phone = new PhoneDao(p.getId(), p.getNumber(), p.getCitycode(), p.getCountrycode(), user);
            System.out.println("TELEFONO: " + phone.toString());
            phonesDao.add(phone);
        }

        return phonesDao;
    }
}
