package org.efire.net.vendomachine_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.efire.net.vendomachine_api.config.CustomNotFoundException;
import org.efire.net.vendomachine_api.domain.Product;
import org.efire.net.vendomachine_api.domain.VendingMachine;
import org.efire.net.vendomachine_api.model.ProductDTO;
import org.efire.net.vendomachine_api.repos.ProductRepository;
import org.efire.net.vendomachine_api.repos.VendingMachineRepository;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final VendingMachineRepository vendingMachineRepository) {
        this.productRepository = productRepository;
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setCode(product.getCode());
        productDTO.setName(product.getName());
        productDTO.setAmount(product.getAmount());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setProducts(product.getProducts() == null ? null : product.getProducts().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setAmount(productDTO.getAmount());
        product.setQuantity(productDTO.getQuantity());
        if (productDTO.getProducts() != null &&
                (product.getProducts() == null || product.getProducts().getId() != productDTO.getProducts())) {
            final VendingMachine products = vendingMachineRepository.findById(productDTO.getProducts())
                    .orElseThrow(CustomNotFoundException::new);
            product.setProducts(products);
        }
        return product;
    }

}
