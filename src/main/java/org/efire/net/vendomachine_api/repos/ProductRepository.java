package org.efire.net.vendomachine_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.efire.net.vendomachine_api.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    // add custom queries here
}
