package ua.andrew1903.expensetracker.service.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ua.andrew1903.expensetracker.db.tables.CategoryTable;
import ua.andrew1903.expensetracker.model.Category;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private static final CategoryTable CATEGORY_TABLE = CategoryTable.TABLE;

    private final DSLContext context;

    public List<Category> getAll() {
        return context.select()
                .from(CATEGORY_TABLE)
                .fetch()
                .into(Category.class);
    }

    public Category getById(Long id) {
        return context.select()
                .from(CATEGORY_TABLE)
                .where(CATEGORY_TABLE.ID.eq(id.intValue()))
                .fetchOne()
                .into(Category.class);
    }

    public Category create(Category category) {
        return context.insertInto(CATEGORY_TABLE)
                .set(CATEGORY_TABLE.NAME, category.getName())
                .set(CATEGORY_TABLE.TYPE, category.getType().name())
                .returning()
                .fetchOne()
                .into(Category.class);
    }
}
