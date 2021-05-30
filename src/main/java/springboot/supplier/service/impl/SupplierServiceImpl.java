package springboot.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.supplier.dao.bean.InventoryTable;
import springboot.supplier.dao.repo.SupplierRepository;
import springboot.supplier.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepo;
	
	Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

	@Override
	public String addInventory(InventoryTable user) {

		String status = "";

		InventoryTable existingData = supplierRepo.findByItemIdContainingIgnoreCase(user.getItemId());
		
        try {
			if(null != existingData) {
				
				if (user.getItemId().equalsIgnoreCase(existingData.getItemId())) {
					existingData.setInventoryCount(existingData.getInventoryCount()+1);
					supplierRepo.saveAndFlush(existingData);
				}
				status = "Success";
			}
			else {
				InventoryTable newInventory = new InventoryTable();
	
				newInventory.setItemId(user.getItemId());
				newInventory.setInventoryCount(1);
				if(user.getInventoryLocation()!=null) {
					newInventory.setInventoryLocation(user.getInventoryLocation());
				}
				else {
					newInventory.setInventoryLocation("");
				}
				supplierRepo.saveAndFlush(newInventory);
				status = "Success";
			} 
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return status;
	}

	@Override
	public String removeInventory(String itemId) {
		String status = "";
		InventoryTable existingData = supplierRepo.findByItemIdContainingIgnoreCase(itemId);

	
			if(existingData != null && existingData.getInventoryCount()!=0) {
				existingData.setInventoryCount(existingData.getInventoryCount()-1);	
				supplierRepo.saveAndFlush(existingData);
				status = "Success";
			}else {
				status = "Failed";
			}
		
		return status;
	}

	@Override
	public List<InventoryTable> viewInventory(String itemId) {
		List<InventoryTable> inventoryData = new ArrayList<InventoryTable>();
		try {
			if (itemId != null) {
				inventoryData = supplierRepo.findAllByItemIdContainingIgnoreCase(itemId);
			} else {
				inventoryData = supplierRepo.findAll();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return inventoryData;
	}

}
