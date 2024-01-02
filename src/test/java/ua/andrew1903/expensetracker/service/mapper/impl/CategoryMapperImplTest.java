package ua.andrew1903.expensetracker.service.mapper.impl;

import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Test;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.model.CategoryType;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.jooq.lambda.Seq.seq;

class CategoryMapperImplTest {

    private final CategoryMapperImpl underTest = new CategoryMapperImpl();

    @Test
    void toCategory() {
        var categoryDto = CategoryDTO.builder()
                .id(1L)
                .name("Category1")
                .type(CategoryType.NECESSARY)
                .build();
        var result = underTest.toCategory(categoryDto);
        var expected = new Category(1L, "Category1", CategoryType.NECESSARY);

        assertCategory(result, expected);
    }

    @Test
    void fromCategory() {
        var category = new Category(2L, "Category2", CategoryType.ACTIVITIES);
        var result = underTest.fromCategory(category);
        var expected = CategoryDTO.builder()
                .id(2L)
                .name("Category2")
                .type(CategoryType.ACTIVITIES)
                .build();

        assertCategoryDto(result, expected);
    }

    @Test
    void toCategoryList() {
        var categoryDtos = List.of(
                CategoryDTO.builder()
                        .id(1L)
                        .name("Category1")
                        .type(CategoryType.ACTIVITIES)
                        .build(),
                CategoryDTO.builder()
                        .id(2L)
                        .name("Category2")
                        .type(CategoryType.NECESSARY)
                        .build(),
                CategoryDTO.builder()
                        .id(3L)
                        .name("Category3")
                        .type(CategoryType.NECESSARY)
                        .build());

        var resultList = underTest.toCategoryList(categoryDtos);

        var expected = List.of(
                new Category(1L, "Category1", CategoryType.ACTIVITIES),
                new Category(2L, "Category2", CategoryType.NECESSARY),
                new Category(3L, "Category3", CategoryType.NECESSARY));

        Seq.zip(seq(resultList), seq(expected))
                .forEach(tuple -> assertCategory(tuple.v1, tuple.v2));
    }

    @Test
    void fromCategoryList() {
        var categoryList = List.of(
                new Category(1L, "Category1", CategoryType.ACTIVITIES),
                new Category(2L, "Category2", CategoryType.NECESSARY),
                new Category(3L, "Category3", CategoryType.ACTIVITIES));

        var resultList = underTest.fromCategoryList(categoryList);

        var expected = List.of(
                CategoryDTO.builder().id(1L).name("Category1").type(CategoryType.ACTIVITIES).build(),
                CategoryDTO.builder().id(2L).name("Category2").type(CategoryType.NECESSARY).build(),
                CategoryDTO.builder().id(3L).name("Category3").type(CategoryType.ACTIVITIES).build());

        Seq.zip(seq(resultList), seq(expected))
                .forEach(tuple -> assertCategoryDto(tuple.v1, tuple.v2));
    }

    private void assertCategory(Category actual, Category expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getType(), is(expected.getType()));
    }

    private void assertCategoryDto(CategoryDTO actual, CategoryDTO expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getType(), is(expected.getType()));
    }
}