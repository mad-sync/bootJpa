package com.example.bootJpa.dao;

import com.example.bootJpa.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepository
public interface AlienRepo extends JpaRepository<Alien, Integer> {


    //writing custom queries
    List<Alien> findByAname(String aname);

    List<Alien> findByAidGreaterThan(int aid);

    @Query("from Alien where tech=?1 order by aname")
    List<Alien> findByTechSorted(String tech);
}
