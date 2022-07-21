package com.evaluacion.bci.userapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.bci.userapp.entity.UserDao;

public interface UserRepository extends JpaRepository<UserDao, String>{
    /*
     * CONSULTA DE LAS TABLAS
     * SELECT * FROM PHONE;

       SELECT * FROM "USER"
     */
    UserDao getReferenceByNameAndPassword(String name, String password);

    boolean existsByEmail(String email);

    
}
