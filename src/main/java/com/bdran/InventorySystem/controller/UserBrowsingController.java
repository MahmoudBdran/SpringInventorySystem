package com.bdran.InventorySystem.controller;

import com.bdran.InventorySystem.dto.ItemDto;
import com.bdran.InventorySystem.dto.converter.ItemConvertor;
import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.service.ItemService;
import com.bdran.InventorySystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserBrowsingController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private VendorService vendorService;
        @Autowired
    private ItemConvertor itemConvertor;
    @GetMapping("/BrowseItems")
    public String viewAllItemsToUser(Model model){
        model.addAttribute("myItems",itemConvertor.modelToDto(itemService.getAllItems()));
        return "UserPages/BrowsingItems";
    }
    @GetMapping("/BrowseItems/{id}")
    public String ItemDetials(@PathVariable("id")Long id, Model model){


        model.addAttribute("myItems",itemConvertor.modelToDto(itemService.getItemById(id)));
        return "UserPages/ItemDetails";
    }




}
