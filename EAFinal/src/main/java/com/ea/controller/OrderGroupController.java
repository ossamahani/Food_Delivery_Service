package com.ea.controller;

import com.ea.domain.OrderGroup;
import com.ea.service.IItemService;
import com.ea.service.IOrderGroupService;
import com.ea.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by ChinzorigD on 9/23/16.
 */

@Controller
public class OrderGroupController {


    @Autowired
    private IOrderService orderDaoService;
    @Autowired
    private IOrderGroupService orderGroupService;
    @Autowired
    IItemService itemService;


    @RequestMapping(value = {"/ordergroup/details/{id}"}, method = RequestMethod.GET)
    public String getOrder(@PathVariable Integer id, Model model) {
        Set<OrderGroup> orderGroups = orderDaoService.findByOrderId(id).getOrderGroups();
        for (OrderGroup g: orderGroups) {
            g.setItem(itemService.findItemById(g.getItem().getId()));
        }
        model.addAttribute("orderGroupList", orderGroups);

        return "ordergroup/details";
    }

    @RequestMapping(value = {"/ordergroup/delete/{id}"}, method = RequestMethod.GET)
    public String delete(@PathVariable Integer id, Model model) {
        orderGroupService.delete(id);
        return "redirect:/order";
    }
}
