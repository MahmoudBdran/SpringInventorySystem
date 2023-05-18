package com.bdran.InventorySystem.controller;

import com.bdran.InventorySystem.dto.ItemDto;
import com.bdran.InventorySystem.dto.converter.ItemConvertor;
import com.bdran.InventorySystem.model.*;
import com.bdran.InventorySystem.service.ItemService;
import com.bdran.InventorySystem.service.ItemTypeService;
import com.bdran.InventorySystem.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import java.io.IOException;

import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Controller
public class ItemController {
        @Autowired
    private ItemService itemService;
    @Autowired
    private ItemTypeService itemTypeService;
    @Autowired
    private VendorService vendorService;
    private Item item;
//    @GetMapping("/")
//    public String home() {
//        // code here
//        return "index";
//    }

    @Autowired
    private ItemConvertor itemConvertor;
    @GetMapping("/ItemView")
    public String View(Model model){
        model.addAttribute("itemDtoList",itemConvertor.modelToDto(itemService.getAllItems()));
        return "/Item/View";
    }

    @GetMapping("/ItemCreate")
    public String create(Model model){
        ItemDto itemDto = new ItemDto();
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("itemTypeList",itemTypeService.getAllItemTypes());
    return "/Item/Create";
    }

    @PostMapping("/ItemCreate")
    public String Create(@Valid @ModelAttribute("itemDto")ItemDto itemDto,
                         BindingResult result,
                         Model model
                         ) throws IOException {
        Vendor vendor =null;
        ItemType itemType=null;
        Item item =null;
        String err= vendorService.validateVendorName(itemDto.getVendorName());
        if(!err.isEmpty()){
            ObjectError error = new ObjectError("globalError",err);
            result.addError(error);
        }else{
            vendor=vendorService.getVendorByName(itemDto.getVendorName());
        }
        err = itemTypeService.validateItemTypeByName(itemDto.getItemType());
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        } else {
            itemType = itemTypeService.getItemTypeByName(itemDto.getItemType());
        }
        err = itemService.validateItemId(itemDto.getItemName(), itemDto.getItemType());
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors()) {
            model.addAttribute("itemDtoList", itemConvertor.modelToDto(itemService.getAllItems()));
            return "/Item/Create";
        }

        //itemDto.setData(decodeImage(itemDto.getData()));
        item = itemConvertor.dtoToModel(itemDto);
        item.setVendor(vendor);
        item.setItemType(itemType);


        this.item =item;

    return "/Item/uploadItemImage";
}



    @PostMapping("/imageUpload")
    public String imageUpload(@RequestParam MultipartFile img) {

        item.setData(img.getOriginalFilename());
        itemService.saveItem(item);
            try {

                File saveFile = new ClassPathResource("static/img").getFile();
                System.out.println("----------------------------------");
                System.out.println(saveFile.getAbsolutePath()+"\\"+ img.getOriginalFilename());

                System.out.println("----------------------------------");
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
                System.out.println(path);
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                item=null;

            } catch (Exception e) {
                e.printStackTrace();
            }

        return "redirect:/ItemView";
    }


    @GetMapping("/ItemEdit/{id}")
    public String Edit(@PathVariable(value = "id") long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("itemDto", itemConvertor.modelToDto(item));
        model.addAttribute("itemTypeList", itemTypeService.getAllItemTypes());

        return "/Item/Edit";
    }

    @PostMapping("edit/{id}")
    public String updateEmployee(@PathVariable Long id ,
                                 @ModelAttribute("itemDto") ItemDto itemDto,
                                 Model model){

        Item item = itemConvertor.dtoToModel(itemDto);

        itemService.updateItem(item);
        return "redirect:/ItemView";
    }



    @GetMapping("/ItemDelete/{id}")
    public String Delete(@PathVariable(value = "id") long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("itemDto", itemConvertor.modelToDto(item));
        return "/Item/Delete";
    }




    @PostMapping("/ItemDelete/{id}")
    public String Delete(@PathVariable(value = "id") long id, @ModelAttribute("itemDto") ItemDto itemDto) {
        itemService.deleteItem(id);
        return "redirect:/ItemView";
    }







}
