package com.evaluacion.bci.userapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evaluacion.bci.userapp.entity.PhoneDao;
import com.evaluacion.bci.userapp.entity.UserDao;
import com.evaluacion.bci.userapp.model.Phone;
import com.evaluacion.bci.userapp.model.User;

@Component
public class UserService {

    @Autowired
    private UserRepository uRepo;
    @Autowired
    private PhoneRepository pRepo;

    UserDao user;
    
    List<PhoneDao> phonesDao = new ArrayList<PhoneDao>();

    public User addUser(User newUser){
        try {
            user = uRepo.save(new UserDao(newUser.getName(), newUser.getEmail(), newUser.getPassword()));

            phonesDao = PhoneDaoMapping(user, newUser.getPhones());
    
            pRepo.saveAll(phonesDao);
            user.setPhones(phonesDao);

            return UserMapping(user);

        } catch (Exception ie){
            return null;
        }
    }

    public User RetrieveUser(String id){
        try{

            user = uRepo.getReferenceById(id);
            return UserMapping(user);

        }catch (Exception e){
            return null;
        }
    }
    
    public User UpdateUser(String id, User user){
        
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

                return UserMapping(userDao);  

            }catch (Exception e){
                return null;
            }
            
        }
        return null;
    }

    public boolean DeleteUser(String id){
        try{

            if (uRepo.existsById(id)) {
                UserDao user = uRepo.getReferenceById(id);
                user.setIsactive(false);
                user.setModified(new Date(System.currentTimeMillis()));

                uRepo.save(user);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * Return the mapping from Object PhoneDao to Phone
     * @param UserDao must not be {@literal null}.
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
     * @param UserDao must not be {@literal null}.
	 * @return a User Object
     * */
    private User UserMapping(UserDao userDao){

        return new User(userDao.getId(), userDao.getName(), userDao.getEmail(), userDao.getPassword(), PhoneMapping(userDao.getPhones()), userDao.getCreated(), userDao.getModified(), userDao.getLast_login(), userDao.Isactive());
    }

 
}
