package ua.andrew1903.expensetracker.service.mapper.impl;

import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.service.mapper.CategoryMapper;
import ua.andrew1903.expensetracker.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toCategory(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( dto.getId() );
        category.name( dto.getName() );
        category.type( dto.getType() );

        return category.build();
    }

    @Override
    public CategoryDTO fromCategory(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();

        categoryDTO.id( category.getId() );
        categoryDTO.name( category.getName() );
        categoryDTO.type( category.getType() );

        return categoryDTO.build();
    }

    @Override
    public List<Category> toCategoryList(List<CategoryDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<>( dtos.size() );
        for ( CategoryDTO categoryDTO : dtos ) {
            list.add( toCategory( categoryDTO ) );
        }

        return list;
    }

    @Override
    public List<CategoryDTO> fromCategoryList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<>( categories.size() );
        for ( Category category : categories ) {
            list.add( fromCategory( category ) );
        }

        return list;
    }
}
