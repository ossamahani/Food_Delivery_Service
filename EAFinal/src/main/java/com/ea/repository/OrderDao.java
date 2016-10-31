package com.ea.repository;

import com.ea.domain.Item;
import com.ea.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChinzorigD on 9/23/16.
 *
 */
@Repository
public interface OrderDao extends JpaRepository<Orders,Integer> {


     Orders save(Orders orders);

    Orders findByOrderId(int orderid);
    List<Orders> findByStatus(int status);

    //@Query("select o from Orders o JOIN o.user u where u.userId = :userId AND o")
    //List<Orders> findAllInCartByUserId(@Param("userId") int userId);
    //@Query(value = "select 1 o from Orders o JOIN o.consumer c where c.id = :consumerid AND o.status =:status LIMIT 1", nativeQuery = true)
    @Query(value = "select o from Orders o JOIN o.consumer c where c.id = :consumerid AND o.status =:status")
    Orders findByConsumerIdStatus(@Param("consumerid") int consumerid, @Param("status") int status);

    @Query("select o from Orders o JOIN o.consumer c where c.id = :consumerid")
    List<Orders> findByConsumerId(@Param("consumerid") int consumerid);
    List<Orders> findAll();



}

