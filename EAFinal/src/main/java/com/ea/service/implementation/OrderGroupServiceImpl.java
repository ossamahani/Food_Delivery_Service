package com.ea.service.implementation;

import com.ea.domain.OrderGroup;
import com.ea.domain.Orders;
import com.ea.repository.OrderDao;
import com.ea.repository.OrderGroupDao;
import com.ea.service.IOrderGroupService;
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
public class OrderGroupServiceImpl implements IOrderGroupService {

    @Autowired
    public OrderGroupDao orderGroupDao;

    public OrderGroupDao getOrderGroupDao() {
        return orderGroupDao;
    }

    public void setOrderGroupDao(OrderGroupDao orderGroupDao) {
        this.orderGroupDao = orderGroupDao;
    }

    public OrderGroup save(OrderGroup orderGroup){
         return orderGroupDao.save(orderGroup);
    }

    @Override
    public void delete(OrderGroup orderGroup){
        orderGroupDao.delete(orderGroup);
    }
    @Override
    public void delete(Integer id){
        orderGroupDao.delete(id);
    }
    @Override
    public OrderGroup findByOrderGroupId(int groupId){
        return orderGroupDao.findByOrderGroupId(groupId);
    }
    @Override
    public List<OrderGroup> findAll(){
        return orderGroupDao.findAll();
    }
    @Override
    public List<OrderGroup> findByOrderId(int orderId){
        return orderGroupDao.findByOrderId(orderId);
    }

}
