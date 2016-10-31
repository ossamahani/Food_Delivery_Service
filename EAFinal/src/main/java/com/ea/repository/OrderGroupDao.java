package com.ea.repository;

import com.ea.domain.OrderGroup;
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
public interface OrderGroupDao extends JpaRepository<OrderGroup,Integer> {

    OrderGroup save(OrderGroup orderGroup);

    OrderGroup findByOrderGroupId(int orderGroupId);

    @Query("select o.orderGroups from Orders o where o.orderId = :orderId")
    List<OrderGroup> findByOrderId(@Param("orderId")int orderId);

    List<OrderGroup> findAll();

}

