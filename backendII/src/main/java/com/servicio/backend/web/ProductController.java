package com.servicio.backend.web;

import com.servicio.backend.entity.Product;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Product>> getAllByExample(@RequestBody Product product){
        return ResponseEntity.ok(productService.findProductByExample(product));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> add(@Valid @RequestBody Product newProduct){
        return ResponseEntity.ok(productService.addProduct(newProduct));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody Product product){
        Product resp = productService.updateProductById(id, product);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (!productService.deleteProducttById(id))
            throw new RecordNotFoundException("Could not find product: " + id);
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}
