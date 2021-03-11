package com.C1020.service.product;

import com.C1020.model.Product;
import com.C1020.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> findProductName(String name);

    Page<Product> findAll(Pageable pageable);

}
