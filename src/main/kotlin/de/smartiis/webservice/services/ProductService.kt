package de.smartiis.webservice.services

import de.smartiis.webservice.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(
    private val productRepository: ProductRepository
) {
  fun getAll() = productRepository.findAll()

  fun getById(id: Int) = productRepository.findByIdOrNull(id)
}
