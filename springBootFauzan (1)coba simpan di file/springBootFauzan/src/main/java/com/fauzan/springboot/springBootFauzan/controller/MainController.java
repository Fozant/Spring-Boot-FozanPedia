package com.fauzan.springboot.springBootFauzan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fauzan.springboot.springBootFauzan.model.Product;
import com.fauzan.springboot.springBootFauzan.service.ProductService;

@Controller
public class MainController {
    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    @GetMapping("/home")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        
        model.addAttribute("products", products);
        return "home"; 
    }
    
   @PostMapping("/search")
    public String search(@RequestParam(name = "keyword") String keyword, Model model) {
    List<Product> searchResults = productService.searchProducts(keyword);
    model.addAttribute("searchResults", searchResults);
    model.addAttribute("keyword", keyword);
    return "searchResults";
}

}
