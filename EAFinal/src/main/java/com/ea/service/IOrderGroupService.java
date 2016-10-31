package com.ea.service;

import com.ea.domain.OrderGroup;
import com.ea.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChinzorigD on 9/25/16.
 */
@Service
public interface IOrderGroupService {

    public abstract OrderGroup save(OrderGroup orderGroup);
    public abstract void delete(OrderGroup orderGroup);
    public abstract void delete(Integer id);
    public abstract OrderGroup findByOrderGroupId(int orderGroupId);
    public abstract List<OrderGroup> findByOrderId(int orderId);
    public abstract List<OrderGroup> findAll();

}
