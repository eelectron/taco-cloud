package tacos.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;
import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
}
