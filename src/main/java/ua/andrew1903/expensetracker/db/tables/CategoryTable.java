package ua.andrew1903.expensetracker.db.tables;

import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import ua.andrew1903.expensetracker.db.tables.records.CategoryRecord;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CategoryTable extends TableImpl<CategoryRecord> {
    public static final CategoryTable TABLE = new CategoryTable();

    @Override
    public Class<CategoryRecord> getRecordType() {
        return CategoryRecord.class;
    }

    public final TableField<CategoryRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");
    public final TableField<CategoryRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");
    public final TableField<CategoryRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(20), this, "");
    public CategoryTable() {
        super(DSL.name("category"));
    }

    private CategoryTable(Name alias, Table<CategoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private CategoryTable(Name alias, Table<CategoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> CategoryTable(Table<O> child, ForeignKey<O, CategoryRecord> key) {
        super(child, key, TABLE);
    }

    @Override
    public Identity<CategoryRecord, Integer> getIdentity() {
        return (Identity<CategoryRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CategoryRecord> getPrimaryKey() {
        return Internal.createUniqueKey(CategoryTable.TABLE, DSL.name("category_id_pk"), new TableField[] { CategoryTable.TABLE.ID }, true);
    }

    @Override
    public CategoryTable as(String alias) {
        return new CategoryTable(DSL.name(alias), this);
    }

    @Override
    public CategoryTable as(Name alias) {
        return new CategoryTable(alias, this);
    }

    @Override
    public CategoryTable as(Table<?> alias) {
        return new CategoryTable(alias.getQualifiedName(), this);
    }
}
