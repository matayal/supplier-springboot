package springboot.supplier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import springboot.supplier.dao.bean.InventoryTable;
import springboot.supplier.service.SupplierService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(maxAge = 3600)
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
		
	Logger logger = LoggerFactory.getLogger(SupplierController.class);
		
  
	@PostMapping(value = "/addInventory")
	public Map<String, Object> addInventory(@RequestBody InventoryTable userData) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = supplierService.addInventory(userData);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Inventory has been successfully added");
		} else {
			response.put("ResponseCode", "400");
			response.put("ResponseText", "Failed to add inventory");
		}
		logger.info("Add inventory response:{", response);

		return response;
	}

	@DeleteMapping(value = "/removeInventory/{itemId}/")
	public Map<String, Object> removeInventory(@PathVariable String itemId) throws Exception {
		Map<String, Object> response = new HashMap();

		String status = supplierService.removeInventory(itemId);

		if (status.equalsIgnoreCase("Success")) {
			response.put("ResponseCode", "204");
			response.put("ResponseText", "Inventory has been removed successfully");
		} else {
			response.put("ResponseCode", "400");
			response.put("ResponseText", "Failed to remove inventory");
		}
		logger.info("Remove Inventory response:{}", response);

		return response;
	}


	@GetMapping(value = "/viewInventory")
	public Map<String, Object> viewInventory(@RequestParam(name = "itemId", required = false) String itemId)
			throws Exception {
		Map<String, Object> response = new HashMap();

		List<InventoryTable> inventoryData = supplierService.viewInventory(itemId);

		if (!CollectionUtils.isEmpty(inventoryData)) {
			response.put("ResponseCode", "200");
			response.put("ResponseText", "Success");
			response.put("ResponseBody", inventoryData);
		} else {
			response.put("ResponseCode", "300");
			response.put("ResponseText", "Failed");
		}
		logger.info("ViewInventory response:{}", response);

		return response;
	}
}

