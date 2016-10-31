package com.ea.service;

import com.ea.domain.Item;
import com.ea.domain.Orders;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChinzorigD on 9/25/16.
 */
@Service
public interface IOrderService {
    public abstract Orders save(Orders orders);
    public abstract void update(Orders orders);
    public abstract void delete(Orders orders);
    public abstract void delete(Integer id);
    public abstract Orders findByOrderId(int orderid);
    public abstract Orders findByConsumerIdStatus(int consumerid, int status);
    public abstract List<Orders> findByStatus(int status);
    public abstract List<Orders> findAll();
    public abstract List<Orders> findByConsumerId(int consumerId);

}
