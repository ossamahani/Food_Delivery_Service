/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.controller;

import com.ea.domain.Item;
import com.ea.service.IBusinessService;
import com.ea.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by amanadhikari on 9/23/16.
 */
@Controller
public class ItemController {
    @Autowired
    IItemService itemService;
    @Autowired
    IBusinessService businessService;

    public IItemService getItemService() {
        return itemService;
    }

    public void setItemService(IItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        map.put("itemList", itemService.findAll());
        return "redirect:/item/list";
    }

    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        map.put("itemList", itemService.findAll());
        return "item/list";
    }

    @RequestMapping(value = "/item/search",method = RequestMethod.POST)
    public String search(Map<String,Object> map, @RequestParam String itemName){
        System.out.println("itemName = " + itemName);
        map.put("itemList", itemService.findAllByName(itemName));
        map.put("isSearch", true);
        return "item/list";
    }

    @RequestMapping(value = "/business/{businessId}/item/list",method = RequestMethod.GET)
    public String listItemOfBusiness(@PathVariable Integer businessId, Map<String,Object> map){
        map.put("itemList",itemService.findAllByBusiness(businessId));
        map.put("businessId",businessId);
        return "item/list";
    }

    @RequestMapping(value = "/business/{businessId}/item/create",method = RequestMethod.GET)
    public String createItem(@PathVariable Integer businessId, Model model){
        Item item = new Item();
        item.setBusiness(businessService.findBusinessById(businessId));
        model.addAttribute("item", item);
        return "item/itemForm";
    }

    @RequestMapping(value = "/item/create",method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("item", new Item());
        return "item/itemForm";
    }

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    public String save(@Valid Item item, BindingResult result){
        System.out.println("item = " + item);
        System.out.println("item = " + item.getBusiness().getName());
        if(!result.hasErrors()) {
            itemService.save(item);
            return "redirect:/business/" + item.getBusiness().getId() + "/item/list";
        }
        return "item/itemForm";

    }

    @RequestMapping("/business/{businessId}/item/edit/{id}")
    public String editItem(@PathVariable Integer businessId,@PathVariable Integer id,Model model){
        model.addAttribute("item", itemService.findItemById(id));
        return "item/itemForm";
    }


    @RequestMapping("/item/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("item", itemService.findItemById(id));
        return "item/itemForm";
    }

    @RequestMapping("/business/{businessId}/item/show/{id}")
    public String showItem(@PathVariable Integer businessId,@PathVariable Integer id,Model model){
        model.addAttribute("item", itemService.findItemById(id));
        return "item/show";
    }

    @RequestMapping("/item/show/{id}")
    public String show(@PathVariable Integer id,Model model){
        model.addAttribute("item", itemService.findItemById(id));
        return "item/show";
    }

    @RequestMapping("/item/delete/{id}")
    public String delete(@PathVariable Integer id){
        itemService.delete(id);
        return "redirect:/item/list";
    }
}
