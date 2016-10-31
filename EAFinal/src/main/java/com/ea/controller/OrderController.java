package com.ea.controller;

import com.ea.domain.Consumer;
import com.ea.domain.OrderGroup;
import com.ea.domain.Orders;
import com.ea.helper.OrderHelper;
import com.ea.service.IItemService;
import com.ea.service.IOrderGroupService;
import com.ea.service.IOrderService;
import com.ea.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by ChinzorigD on 9/23/16.
 */

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderGroupService orderGroupService;
    @Autowired
    private IItemService itemService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/delivery/orderboard"}, method = RequestMethod.GET)
    public String getOrderBoard(Model model) {
        List<Orders> orderList = orderService.findByStatus(2);

        for (Orders o:orderList) {
            o.setConsumer((Consumer)userService.getUserById(o.getConsumer().getId()));
            List<OrderGroup> orderGroupList = orderGroupService.findByOrderId(o.getOrderId());
            for (OrderGroup og:orderGroupList) {
                og.setItem(itemService.findItemById(og.getItem().getId()));
            }
            o.setOrderGroups(new HashSet<OrderGroup>(orderGroupList));
        }

        model.addAttribute("orderList", orderList);
        return "order/orderboard";
    }

    @RequestMapping(value = {"/delivery/orderedit"}, method = RequestMethod.POST)
    @ResponseBody
    public String orderEdit(@Validated @RequestBody OrderHelper jsonString) {
        OrderHelper orderHelper = jsonString;
        Orders orders =  orderService.findByOrderId(orderHelper.getOrderId());
        orders.setTravelledMiles(orderHelper.getTravelledMiles());
        orders.setStatus(3);
        orderService.update(orders);
        return "200";
    }

    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String getOrder(Model model, HttpSession session) {
        int orderID = 0;

        //Consumer consumer = (Consumer) userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Consumer consumer = (Consumer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Consumer consumer = (Consumer) session.getAttribute("user");
        List<Orders> orderList = orderService.findByConsumerId(consumer.getId());
        for (Orders o:orderList) {
            if(o.getStatus() == 1){
                orderID = o.getOrderId();
                continue;
            }
        }

        if(orderID != 0){
            model.addAttribute("cartList", orderGroupService.findByOrderId(orderID));
        }

        model.addAttribute("orderList", orderList);
        return "order/orderlist";
    }

    @RequestMapping(value = {"/order/create"}, method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        //Consumer consumer = (Consumer) userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Consumer consumer = (Consumer) session.getAttribute("user");
        Orders orders = orderService.findByConsumerIdStatus(consumer.getId(), 1);
        orders.setStatus(2);
        orders.setDate(new Date());
        int totalPrice = 0;
        for (OrderGroup og: orders.getOrderGroups()) {
            totalPrice += itemService.findItemById(og.getItem().getId()).getPrice();
        }
        orders.setTotalPrice(totalPrice);
        orderService.update(orders);
        return "redirect:/order";
    }



    @RequestMapping(value = {"/cart/add/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        int orderID = 0;
        //Consumer consumer = (Consumer) userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //Consumer consumer = (Consumer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Consumer consumer = (Consumer) session.getAttribute("user");
        List<Orders> ordersList = orderService.findByConsumerId(consumer.getId());
        for (Orders o:ordersList) {
            if(o.getStatus() == 1){
                orderID = o.getOrderId();
                continue;
            }
        }
        OrderGroup orderGroup = new OrderGroup();
        if(orderID == 0){
            Orders orders = new Orders();
            orders.setConsumer(consumer);
            orders.setStatus(1);
            orderService.save(orders);
            orderGroup.setOrder(orders);
        }else{
            orderGroup.setOrder(orderService.findByOrderId(orderID));
        }
        orderGroup.setItem(itemService.findItemById(id));
        orderGroupService.save(orderGroup);
        return "200";
    }
}