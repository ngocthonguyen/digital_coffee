package com.digitalcoffee.customer.repository;

import com.digitalcoffee.customer.model.CustomerRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerRoot,Long> {

    Optional<CustomerRoot> findByUsername(String username);
}
