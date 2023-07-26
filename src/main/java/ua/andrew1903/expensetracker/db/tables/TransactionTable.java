package ua.andrew1903.expensetracker.db.tables;

import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import ua.andrew1903.expensetracker.db.tables.records.TransactionRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TransactionTable extends TableImpl<TransactionRecord> {
    public static final TransactionTable TABLE = new TransactionTable();

    @Override
    public Class<TransactionRecord> getRecordType() {
        return TransactionRecord.class;
    }

    public final TableField<TransactionRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    public final TableField<TransactionRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    public final TableField<TransactionRecord, Double> AMOUNT = createField(DSL.name("amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    public final TableField<TransactionRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    public final TableField<TransactionRecord, Integer> CATEGORY_ID = createField(DSL.name("category_id"), SQLDataType.INTEGER, this, "");

    private TransactionTable(Name alias, Table<TransactionRecord> aliased) {
        this(alias, aliased, null);
    }

    private TransactionTable(Name alias, Table<TransactionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public TransactionTable(String alias) {
        this(DSL.name(alias), TABLE);
    }

    public TransactionTable(Name alias) {
        this(alias, TABLE);
    }

    public TransactionTable() {
        this(DSL.name("transaction"), null);
    }

    public <O extends Record> TransactionTable(Table<O> child, ForeignKey<O, TransactionRecord> key) {
        super(child, key, TABLE);
    }

    @Override
    public Identity<TransactionRecord, Integer> getIdentity() {
        return (Identity<TransactionRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TransactionRecord> getPrimaryKey() {
        return Internal.createUniqueKey(TransactionTable.TABLE, DSL.name("KEY_transaction_PRIMARY"), new TableField[] { TransactionTable.TABLE.ID }, true);
    }

    @Override
    public List<ForeignKey<TransactionRecord, ?>> getReferences() {
        return Arrays.asList(Internal.createForeignKey(TransactionTable.TABLE, DSL.name("category_id_fk"), new TableField[] { TransactionTable.TABLE.CATEGORY_ID }, CategoryTable.TABLE.getPrimaryKey(), new TableField[] { CategoryTable.TABLE.ID }, true));
    }

    private transient CategoryTable _categoryTable;

    public CategoryTable category() {
        if (_categoryTable == null)
            _categoryTable = new CategoryTable(this, Internal.createForeignKey(TransactionTable.TABLE, DSL.name("category_id_fk"), new TableField[] { TransactionTable.TABLE.CATEGORY_ID }, CategoryTable.TABLE.getPrimaryKey(), new TableField[] { CategoryTable.TABLE.ID }, true));

        return _categoryTable;
    }

    @Override
    public TransactionTable as(String alias) {
        return new TransactionTable(DSL.name(alias), this);
    }

    @Override
    public TransactionTable as(Name alias) {
        return new TransactionTable(alias, this);
    }

    @Override
    public TransactionTable as(Table<?> alias) {
        return new TransactionTable(alias.getQualifiedName(), this);
    }
}
