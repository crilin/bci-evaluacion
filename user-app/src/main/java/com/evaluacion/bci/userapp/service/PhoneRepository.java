package com.evaluacion.bci.userapp.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.bci.userapp.entity.PhoneDao;

public interface PhoneRepository extends JpaRepository<PhoneDao, Long> {
    
    //List<PhoneDao> findAllByIdUser(String idUser);
}
