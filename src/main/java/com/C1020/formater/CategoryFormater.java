package com.C1020.formater;


import com.C1020.model.Category;
import com.C1020.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormater implements Formatter<Category> {
        private ICategoryService categoryService;
        @Autowired
        public CategoryFormater(ICategoryService categoryService) {
            this.categoryService = categoryService;
        }


    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text));
    }
    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}

