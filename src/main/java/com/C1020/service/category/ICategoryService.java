package com.C1020.service.category;

import com.C1020.model.Category;
import com.C1020.service.IService;

public interface ICategoryService extends IService<Category> {
    Category findByName (String name);
}
