package com.bdran.InventorySystem.dto.converter;


import com.bdran.InventorySystem.dto.ItemDto;
import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.model.ItemType;
import com.bdran.InventorySystem.model.Vendor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemConvertor {

	public ItemDto modelToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(item.getId());
		itemDto.setFineRate(item.getFineRate());
//		itemDto.setData(item.getData());
		itemDto.setData(item.getData());
		itemDto.setItemPrice(item.getPrice());
		itemDto.setInvoiceNumber(item.getInvoiceNumber());
		itemDto.setItemName(item.getName());
		itemDto.setItemQuantity(item.getQuantity());
		try {
			itemDto.setItemType(item.getItemType().getTypeName());
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception Caught in Item Convertor.");
			itemDto.setItemType("-");
		}
		try {
			itemDto.setVendorName(item.getVendor().getName());
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception Caught in Item Convertor.");
			itemDto.setVendorName("-");
		}

		return itemDto;
	}

	public List<ItemDto> modelToDto(List<Item> itemList) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		for (Item item : itemList) {
			itemDtoList.add(modelToDto(item));
		}
		return itemDtoList;
	}

	public Item dtoToModel(ItemDto itemDto) {
		ItemType itemType=new ItemType();
		Vendor vendor = new Vendor();
		vendor.setName(itemDto.getVendorName());
		itemType.setTypeName(itemDto.getItemType());
		Item item = new Item();
		item.setId(itemDto.getItemId());
		item.setItemType(itemType);
		item.setVendor(vendor);
		item.setData(itemDto.getData());
		item.setFineRate(itemDto.getFineRate());
		item.setInvoiceNumber(itemDto.getInvoiceNumber());
		item.setQuantity(itemDto.getItemQuantity());
		item.setPrice(itemDto.getItemPrice());
		item.setName(itemDto.getItemName());
		return item;
	}
}
