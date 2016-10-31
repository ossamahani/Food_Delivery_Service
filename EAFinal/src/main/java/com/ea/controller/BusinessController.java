/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.controller;

import com.ea.domain.Business;
import com.ea.service.IBusinessService;
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
 * Created by amanadhikari on 9/23/16.
 */
@Controller
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    IBusinessService businessService;

    public IBusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }

    private static final String REDIRECT_URL_LIST_CONSUMER="dashboard";
    private static final String REDIRECT_URL_LIST_BUSINESS="list";

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @RequestMapping(value="/dashboard")
    public String dashboard(HttpSession session) {
        Business business = (Business)session.getAttribute("user");
        System.out.println("business = " + business);
        return "redirect:myBusiness/"+business.getId();
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        map.put("businessList", businessService.findAll());
        return "redirect:/business/list";
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        map.put("businessList", businessService.findAll());
        return "business/list";
    }

    @PreAuthorize("hasRole('ROLE_BUSINESS')")
    @RequestMapping(value = "myBusiness/{businessId}",method = RequestMethod.GET)
    public String listForBusiness(Map<String,Object> map,@PathVariable Integer businessId){
        map.put("businessList", businessService.findBusinessById(businessId));
        return "business/list";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("business", new Business());
        return "business/businessForm";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Business business, Model model){
        if(!business.getName().equals("") && !business.getName().equals("") && !business.getBusinessType().equals(""))
        {
            businessService.save(business);
            return "redirect:/business/" + REDIRECT_URL_LIST_CONSUMER;
        }
        List<String> errors = new ArrayList<>();
        if (business.getName().equals("")) {
            errors.add("Name should not be empty");
        }
        if(business.getBusinessType().equals(""))
        {
            errors.add("Business Type should not be empty");
        }
        if(business.getDescription().equals("")){
            errors.add("Description should not be empty");
        }
        model.addAttribute("errors", errors);
        return "business/businessForm";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("business", businessService.findBusinessById(id));
        return "business/businessForm";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id,Model model){
        model.addAttribute("business", businessService.findBusinessById(id));
        return "business/show";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        businessService.delete(id);
        return "redirect:business/list";
    }
}
