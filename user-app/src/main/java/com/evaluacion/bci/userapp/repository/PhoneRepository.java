package com.evaluacion.bci.userapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.bci.userapp.entity.PhoneDao;
import com.evaluacion.bci.userapp.entity.UserDao;

public interface PhoneRepository extends JpaRepository<PhoneDao, Long> {
    
    List<PhoneDao> findAllByUser (UserDao user);

    void deleteAllByUser(UserDao idUser);
}
