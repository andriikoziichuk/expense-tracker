package ua.andrew1903.expensetracker.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ua.andrew1903.expensetracker.db.tables.CategoryTable;
import ua.andrew1903.expensetracker.model.Category;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final DSLContext context;

    public List<Category> getAll() {
        return context.select()
                .from(CategoryTable.TABLE)
                .fetch()
                .into(Category.class);
    }

    public Category getById(Long id) {
        return context.select()
                .from(CategoryTable.TABLE)
                .where(CategoryTable.TABLE.ID.eq(id.intValue()))
                .fetchOne()
                .into(Category.class);
    }

    public Category create(Category category) {
        return context.insertInto(CategoryTable.TABLE)
                .set(CategoryTable.TABLE.NAME, category.getName())
                .set(CategoryTable.TABLE.TYPE, category.getType().name())
                .returning()
                .fetchOne()
                .into(Category.class);
    }
}
