package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
    private final PartRepository partRepository;
    private ProductRepository productRepository;

    @Autowired

    public ProductServiceImpl(ProductRepository productRepository, PartRepository partRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Long theIdl=(long)theId;
        Optional<Product> result = productRepository.findById(theIdl);

        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            // we didn't find the product id
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return theProduct;
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl=(long)theId;
        productRepository.deleteById(theIdl);
    }
    public List<Product> listAll(String keyword){
        if(keyword !=null){
            return productRepository.search(keyword);
        }
        return (List<Product>) productRepository.findAll();
    }
    public String buyProduct(int theId) {
        Product product = findById(theId);
        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            save(product);
            return "Product id " + theId + " has been purchased";
        } else {
            return "Sorry, product id " + theId + " is out of stock";
        }
    }

    @Override
    public void save(Product theProduct) {
        try {
            validateProduct(theProduct);
            productRepository.save(theProduct);
        } catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
        List<Product> existingProducts = (List<Product>) productRepository.findAll();
        for (Product product : existingProducts) {
            if (product.getName().equalsIgnoreCase(theProduct.getName())) {
                theProduct.setName(theProduct.getName() + " (Multi-pack)");
                break;
            }
        }
        productRepository.save(theProduct);
    }

    public void validateProduct(Product product) throws IllegalArgumentException {
        for (Part part : product.getParts()) {
            if (part.getInv() < part.getMin()) {
                throw new IllegalArgumentException("Error: Adding this product lowers the part inventory below the minimum allowed.");
            }
        }
    }

}
