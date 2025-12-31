package dev.gabrielsales.provider.repository;

import dev.gabrielsales.provider.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductInfo, Long> {

    @Query("SELECT p FROM ProductInfo p WHERE p.slug = :slug")
    Optional<ProductInfo> findBySlug(@Param("slug") String slug);

}
