package springboot.supplier.dao.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.supplier.dao.bean.InventoryTable;

@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<InventoryTable, String> {


	InventoryTable findByItemIdContainingIgnoreCase(String itemId);

	List<InventoryTable> findAllByItemIdContainingIgnoreCase(String itemId);

}
