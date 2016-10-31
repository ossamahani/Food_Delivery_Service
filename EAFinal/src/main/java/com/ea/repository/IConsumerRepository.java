package com.ea.repository;

import com.ea.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IConsumerRepository extends JpaRepository<Consumer,Integer> {

}
