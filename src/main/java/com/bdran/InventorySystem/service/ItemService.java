package com.bdran.InventorySystem.service;

import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item getItemById(long id){
        Optional<Item> items = itemRepository.findById(id);
        Item item = null;
        if(items.isPresent()){
            item=items.get();

        }else{
            //Exception
        }
        return item;
    }


    public String ValidateItemId(long id){
        String errorMessage="";
        Item item = getItemById(id);
        if(item==null){
            errorMessage="Item id does not exist";
        }
        return errorMessage;
    }

    public List<Item>getAllItems(){
        return itemRepository.findAll();
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }
    public String validateItemId(String itemName,String itemType){
        String errorMessage="";
        List<Item> itemList=getAllItems();
        for(Item item :itemList){
            if(item.getName().equalsIgnoreCase(itemName)
                    & item.getItemType().getTypeName().equalsIgnoreCase(itemType)){
                errorMessage = "Item already exists in the database. Cannot add.";

            }
        }return errorMessage;

    }

    public void deleteItem(long itemId){
        Item item = getItemById(itemId);
        itemRepository.delete(item);
    }
}
