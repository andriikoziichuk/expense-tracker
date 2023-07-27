package ua.andrew1903.expensetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.service.mapper.CategoryMapper;
import ua.andrew1903.expensetracker.service.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper mapper = CategoryMapper.MAPPER;

    private final CategoryRepository repository;

    public CategoryDTO create(CategoryDTO category) {
        Category cat = mapper.toCategory(category);
        return mapper.fromCategory(repository.create(cat));
    }

    public List<CategoryDTO> getAll() {
        return mapper.fromCategoryList(repository.getAll());
    }

    public CategoryDTO getById(Long id) {
        return mapper.fromCategory(repository.getById(id));
    }
}
