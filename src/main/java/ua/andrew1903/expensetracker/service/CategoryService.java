package ua.andrew1903.expensetracker.service;

import ua.andrew1903.expensetracker.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO create(CategoryDTO category);

    List<CategoryDTO> getAll();

    CategoryDTO getById(Long id);
}
