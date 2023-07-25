package ua.andrew1903.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.service.CategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/api/categories")
    public Map<String, CategoryDTO> create(@RequestBody CategoryDTO category) {
        return Map.of(
                "created",
                service.create(category)
        );
    }

    @GetMapping("/api/categories")
    public Map<String, List<CategoryDTO>> getAll() {
        return Map.of(
                "categories",
                service.getAll()
        );
    }

    @GetMapping("/api/categories/{id}")
    public Map<Long, CategoryDTO> getAll(@PathVariable Long id) {
        return Map.of(
                id,
                service.getById(id)
        );
    }
}
