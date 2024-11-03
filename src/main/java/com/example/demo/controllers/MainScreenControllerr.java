package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 *
 *
 *
 *
 */

@Controller
public class MainScreenControllerr {
    //private final PartRepository partRepository;
    //private final ProductRepository productRepository;

    private PartService partService;
    private ProductService productService;

    private List<Part> theParts;
    private List<Product> theProducts;

    /*public MainScreenControllerr(PartRepository partRepository, ProductRepository productRepository) {
     //   this.partRepository = partRepository;
     //   this.productRepository = productRepository;
   }*/

    public MainScreenControllerr(PartService partService,ProductService productService){
        this.partService=partService;
        this.productService=productService;
    }
    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword){
        //add to the sprig model
        List<Part> partList = partService.listAll(partkeyword);
        if (partList == null) {
            partList = List.of(); // Use an empty list if the result is null
        }
        theModel.addAttribute("parts", partList);

        theModel.addAttribute("parts",partList);
        theModel.addAttribute("partkeyword",partkeyword);
    //    theModel.addAttribute("products",productService.findAll());
        List<Product> productList = productService.listAll(productkeyword);
        if (productList == null) {
            productList = List.of(); // Use an empty list if the result is null
        }
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword",productkeyword);
        return "mainscreen";
    }
    @GetMapping("/")
    public String mainscreenReturn() {
        return "mainscreen";
    }
    @GetMapping("/about")
    public String aboutPageReturn() {
        return "about";
    }
    @GetMapping("/buyProduct")
    public String buyProductReturn(@Param("productID") int productID, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productID);
        if (product != null && product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            redirectAttributes.addFlashAttribute("message", "Nice Purchase!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Sorry, looks like we are all out of this product.");
        }
        return "redirect:/mainscreen";
    }
    @Configuration
    public class SampleInventoryInitializer {
        @Bean
        public CommandLineRunner initializeInventory(PartService partService, ProductService productService) {
            return args -> {
                if (partService.findAll().isEmpty() && productService.findAll().isEmpty()) {
                    InhousePart part1;
                    part1 = new InhousePart("Part 1", 5.0, 10, 5, 20, 123);
                    OutsourcedPart part2;
                    part2 = new OutsourcedPart("Widget 2", 8.5, 15, 3, 30, "Samsung");
                    InhousePart part3;
                    part3 = new InhousePart("Gadget 3", 3.5, 20, 10, 50, 124);
                    InhousePart part4;
                    part4 = new InhousePart("Part 4", 7.0, 12, 2, 25, 125);
                    InhousePart part5;
                    part5 = new InhousePart("Component 5", 6.0, 25, 8, 40, 126);

                    partService.save(part1);
                    partService.save(part2);
                    partService.save(part3);
                    partService.save(part4);
                    partService.save(part5);

                    // Sample Products
                    Product product1 = new Product("Product First", 50.0, 5);
                    Product product2 = new Product("Product Second", 75.0, 8);
                    Product product3 = new Product("Product Third", 30.0, 12);
                    Product product4 = new Product("Product Fourth", 45.0, 10);
                    Product product5 = new Product("Product Fifth", 60.0, 7);

                    productService.save(product1);
                    productService.save(product2);
                    productService.save(product3);
                    productService.save(product4);
                    productService.save(product5);

                    System.out.println("Sample Inventory Initialized");
                } else {
                    System.out.println("Inventory Already Exists");
                }
            };
        }
    }
}
