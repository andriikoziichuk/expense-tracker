package ua.andrew1903.expensetracker.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import ua.andrew1903.expensetracker.model.CategoryType;


@Getter
@Builder
@Jacksonized
public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryType type;
}

