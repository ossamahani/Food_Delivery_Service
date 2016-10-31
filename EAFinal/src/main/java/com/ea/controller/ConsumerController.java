/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.controller;

import com.ea.domain.Consumer;
import com.ea.service.IBusinessService;
import com.ea.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ossama Hani on 9/27/16.
 */

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    IConsumerService consumerService;
    @Autowired
    IBusinessService businessService;

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @RequestMapping(value="/dashboard")
    public String dashboard(Map<String,Object> map,HttpSession session) {
        Consumer consumer = (Consumer) session.getAttribute("user");
        map.put("businessList", businessService.findAll());
        map.put("consumerList", consumerService.findConsumerById(consumer.getId()));
        return "consumer/dashboard";
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        map.put("consumerList", consumerService.findAll());
        return "redirect:/consumer/list";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        map.put("consumerList", consumerService.findAll());
        return "consumer/list";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("consumer", new Consumer());
        return "consumer/consumerForm";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Consumer consumer, Model model){
        if (!consumer.getFirstName().equals("") && !consumer.getLastName().equals("") && !consumer.getAddress().equals("")) {
            consumerService.save(consumer);
            return "redirect:/consumer/dashboard";
        }

        List<String> errors = new ArrayList<>();
        if (consumer.getFirstName().equals("")) {
            errors.add("First Name should not be empty");
        }
        if(consumer.getLastName().equals(""))
        {
            errors.add("Last Name should not be empty");
        }
        if(consumer.getAddress().equals("")){
            errors.add("Address should not be empty");
        }
        model.addAttribute("errors", errors);
        return "consumer/consumerForm";

    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("consumer", consumerService.findConsumerById(id));
        return "consumer/consumerForm";
    }


    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id,Model model){
        model.addAttribute("consumer", consumerService.findConsumerById(id));
        return "consumer/show";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        consumerService.delete(id);
        return "redirect:consumer/list";
    }
}
