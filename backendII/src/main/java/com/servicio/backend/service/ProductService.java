package com.servicio.backend.service;

import com.servicio.backend.entity.Product;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(long id){
        return productRepo.findById(id);
    }

    public Product addProduct(Product newProduct){
        newProduct.setCreate(new Date());
        return productRepo.save(newProduct);
    }

    public Product updateProductById(Long id_product, Product product){
        Product resp = productRepo.findById(id_product).get();
        resp.setId_product(id_product);
        if (product.getAuthor() != null)
            resp.setAuthor(product.getAuthor());
        if (product.getName() != null)
            resp.setName(product.getName());
        if (product.getName() != null)
            resp.setName(product.getName());
        if (product.getPrice() != null)
            resp.setPrice(product.getPrice());
        if (product.getPicture() != null)
            resp.setPicture(product.getPicture());
        return productRepo.save(resp);
    }

    public boolean deleteProducttById(long id) throws RecordNotFoundException {
        if (!productRepo.existsById(id))
            throw new RecordNotFoundException("Could not find producto: " + id);
        productRepo.deleteById(id);
        return !productRepo.existsById(id);
    }

    public List<Product> findProductByExample(Product product){
        return productRepo.findAll(Example.of(product));
    }

    public boolean existProductById(long id){
        return productRepo.existsById(id);
    }

    public boolean existProductByExample(Product exampleProduct){
        return productRepo.exists(Example.of(exampleProduct));
    }
}
