package com.evaluacion.bci.userapp.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        //List<Phone> phones = new ArrayList<Phone>();
        PhoneDao phone;
        UserDao user = uRepo.save(new UserDao(newUser.getName(), newUser.getEmail(), newUser.getPassword()));

        for (Phone p: newUser.getPhones()){
            phone = new PhoneDao(p.getNumber(), p.getCitycode(), p.getCountrycode(),user);
            System.out.println("Phone: " + phone.toString());
            phonesDao.add(phone);
            //phones.add(p);
  //          pRepo.save(phone);
        }
        pRepo.saveAll(phonesDao);
//        user.setPhones(phonesDao);
        
        return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(), newUser.getPhones(), user.getCreated(), user.getModified(), user.getLast_login(), user.Isactive());
    }

    @GetMapping("/users/{id}")
    public User RetrieveUser(@PathVariable String id){

        UserDao user = uRepo.getReferenceById(id);
        //List<PhoneDao> phones = pRepo.findAllByIdUser(id);
        //user.setPhones(phones);

        return UserMapping(user);

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
            phone = new Phone(p.getNumber(), p.getCitycode(), p.getCountrycode());
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
            phone = new PhoneDao(p.getNumber(), p.getCitycode(), p.getCountrycode(), user);
            phonesDao.add(phone);
        }

        return phonesDao;
    }
}
