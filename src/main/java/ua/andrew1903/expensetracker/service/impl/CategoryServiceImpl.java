package ua.andrew1903.expensetracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.mapper.CategoryMapper;
import ua.andrew1903.expensetracker.repository.CategoryRepository;
import ua.andrew1903.expensetracker.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper mapper = CategoryMapper.MAPPER;

    private final CategoryRepository repository;

    @Override
    public CategoryDTO create(CategoryDTO category) {
        Category cat = mapper.toCategory(category);
        return mapper.fromCategory(repository.create(cat));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return mapper.fromCategoryList(repository.getAll());
    }

    @Override
    public CategoryDTO getById(Long id) {
        return mapper.fromCategory(repository.getById(id));
    }
}
