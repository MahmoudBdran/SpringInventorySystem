package com.bdran.InventorySystem.repository;

import com.bdran.InventorySystem.model.Item;
import com.bdran.InventorySystem.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType,Long> {

    ItemType findItemTypeByTypeName(String name);
}
