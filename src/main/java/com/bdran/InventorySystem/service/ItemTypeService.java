package com.bdran.InventorySystem.service;

import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.model.ItemType;
import com.bdran.InventorySystem.repository.ItemRepository;
import com.bdran.InventorySystem.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemTypeService {

    @Autowired
    private ItemTypeRepository itemTypeRepository;

   public void saveItemType(ItemType itemType){
       itemTypeRepository.save(itemType);

   }


   public List<ItemType> getAllItemTypes(){
    return   itemTypeRepository.findAll();

   }
   public ItemType getItemTypeByName(String name){
       return itemTypeRepository.findItemTypeByTypeName(name);
   }
   public String validateItemTypeByName(String name){
       String errorMessage="";
       ItemType itemType =getItemTypeByName(name);
       if(itemType==null){
           errorMessage="ItemType with name: "+name+" does not exist";
       }
       return errorMessage;
   }

}
