package com.C1020.controller;


import com.C1020.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.C1020.service.product.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

//    @GetMapping
//    public ModelAndView getAll() {
//        List<Product> products = productService.findAll();
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("product", products);
//        return modelAndView;
//    }

    @GetMapping
    public ModelAndView showAll(@PageableDefault (size = 5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> productPage = productService.findAllPage(pageable);
        modelAndView.addObject("product",productPage);
        return modelAndView;
    }

    @GetMapping("/creat")
    public ModelAndView showCreatProductForm() {
        ModelAndView modelAndView = new ModelAndView("creat");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/creat")
    public ModelAndView CreatProductForm(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect: /product");
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@ModelAttribute Product product, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect: /product");
        product.setId(id);
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteProduct (@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("redirect: /product");
        productService.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchByProductName(@RequestParam String search){
        List<Product> list = productService.findProductName(search);
        return new ModelAndView("list","product",list);
    }
}
