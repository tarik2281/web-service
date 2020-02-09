package de.smartiis.webservice.repositories

import de.smartiis.webservice.entities.Product
import de.smartiis.webservice.entities.ProductVariant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductVariantRepository : JpaRepository<ProductVariant, Int> {

}

@Repository
interface ProductRepository : JpaRepository<Product, String> {

}
