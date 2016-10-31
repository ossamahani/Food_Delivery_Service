package com.ea.service.implementation;

import com.ea.domain.Orders;
import com.ea.repository.OrderDao;
import com.ea.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChinzorigD on 9/25/16.
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    public OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void update(Orders orders) {
        orderDao.save(orders);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Orders save(Orders orders){
        return orderDao.save(orders);
    }


//    public Orders update(Orders orders){
//        return orderDao.update(orders);
//    }

    @Override
    public void delete(Orders orders){
        orderDao.delete(orders);
    }
    @Override
    public void delete(Integer id){
        orderDao.delete(id);
    }
    @Override
    public List<Orders> findByStatus(int status){
        return orderDao.findByStatus(status);
    }
    @Override
    public List<Orders> findAll(){
        return orderDao.findAll();
    }
    @Override
    public List<Orders> findByConsumerId(int consumerid){
        return orderDao.findByConsumerId(consumerid);
    }
    @Override
    public Orders findByConsumerIdStatus(int consumerid, int status){
        return orderDao.findByConsumerIdStatus(consumerid, status);
    }
    @Override
    public Orders findByOrderId(int orderid){
        return orderDao.findByOrderId(orderid);
    }
}
