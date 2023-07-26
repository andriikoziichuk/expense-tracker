package ua.andrew1903.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/api/categories")
    public CategoryDTO create(@RequestBody CategoryDTO category) {
        return service.create(category);
    }

    @GetMapping("/api/categories")
    public List<CategoryDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/api/categories/{id}")
    public CategoryDTO getAll(@PathVariable Long id) {
        return service.getById(id);
    }
}
