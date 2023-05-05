package com.bdran.InventorySystem.service;

import com.bdran.InventorySystem.dto.ItemDto;
import com.bdran.InventorySystem.model.Images;
import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public void updateItem(Item item){
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




    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
    public ItemDto saveProductToDB(MultipartFile data ,
                                   String itemName,
                                   String itemPrice,
                                   String itemQuantity,
                                   String fineRate,
                                   String invoiceNumber,
                                   String vendorName,
                                   String itemType){
        ItemDto itemDto = new ItemDto();
        String fileName = StringUtils.cleanPath(data.getOriginalFilename());
        itemDto.setData(fileName);
        itemDto.setItemType(itemType);
        itemDto.setItemName(itemName);
        itemDto.setItemPrice(Double.parseDouble(itemPrice));
        itemDto.setItemQuantity(Integer.parseInt(itemQuantity));
        itemDto.setVendorName(vendorName);
        itemDto.setInvoiceNumber(Long.parseLong(invoiceNumber));
        itemDto.setFineRate(Double.parseDouble(fineRate));
        LOGGER.info("---------------------- " +itemName);
        LOGGER.info("---------------------- " +vendorName);
        LOGGER.info("---------------------- " +StringUtils.cleanPath(data.getOriginalFilename()));
            try {

                File saveFile = new ClassPathResource("static/img").getFile();
                System.out.println("----------------------------------");
                System.out.println(saveFile.getAbsolutePath()+"\\"+ data.getOriginalFilename());

                System.out.println("----------------------------------");
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + data.getOriginalFilename());
                System.out.println(path);
                Files.copy(data.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                String filePath = "C:\\Users\\utd\\AppData\\Local\\Temp\\";
//
//                File file = new File(filePath);
//
//                if (file.exists()) {
//                    file.delete();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }

return itemDto;
    }
}
