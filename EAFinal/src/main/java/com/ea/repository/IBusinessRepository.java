package com.ea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ea.domain.Business;


@Repository
public interface IBusinessRepository extends JpaRepository<Business,Integer> {

}
