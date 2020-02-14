package com.elsa.customer.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.elsa.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value = "select * from  customer where name LIKE \':name\'", nativeQuery = true)
    List<Customer> findByName(@Param("name") String name);
	
}
