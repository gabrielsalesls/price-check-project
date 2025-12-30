package dev.gabrielsales.provider.repository;

import dev.gabrielsales.provider.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

    @Query("select p from ProductInfo p where p.name = :name")
    List<ProductInfo> findByProductName(
            @Param("name") String name
    );

}
