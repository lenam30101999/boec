package com.spring.boec.repositories;

import com.spring.boec.model.product.ProductFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite, Integer> {
    List<ProductFavorite> findAllByCustomerId(int customerId);
}
