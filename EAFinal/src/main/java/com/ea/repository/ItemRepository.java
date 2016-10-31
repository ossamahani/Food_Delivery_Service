/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.repository;

import com.ea.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amanadhikari on 9/23/16.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query("from Item i where i.business.id = :businessId")
    List<Item> findAllByBusiness(@Param("businessId") Integer businessId);

    @Query("from Item i where i.name LIKE CONCAT('%',:itemName,'%')")
    List<Item> findAllByName(@Param("itemName") String itemName);
}
