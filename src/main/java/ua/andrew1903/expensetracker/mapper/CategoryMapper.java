package ua.andrew1903.expensetracker.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.mapper.impl.CategoryMapperImpl;
import ua.andrew1903.expensetracker.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = new CategoryMapperImpl();

    Category toCategory(CategoryDTO dto);

    @InheritInverseConfiguration
    CategoryDTO fromCategory(Category category);

    List<Category> toCategoryList(List<CategoryDTO> dtos);

    List<CategoryDTO> fromCategoryList(List<Category> categories);
}
