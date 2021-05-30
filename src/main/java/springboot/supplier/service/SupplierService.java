package springboot.supplier.service;

import java.util.List;

import springboot.supplier.dao.bean.InventoryTable;

public interface SupplierService {

	public String addInventory(InventoryTable user);

	public String removeInventory(String itemId);

	public List<InventoryTable> viewInventory(String itemId);

}
