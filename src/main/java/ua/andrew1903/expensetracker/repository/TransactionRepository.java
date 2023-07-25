package ua.andrew1903.expensetracker.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ua.andrew1903.expensetracker.db.tables.CategoryTable;
import ua.andrew1903.expensetracker.db.tables.TransactionTable;
import ua.andrew1903.expensetracker.dto.TransactionJoinCategory;
import ua.andrew1903.expensetracker.model.Transaction;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final DSLContext context;

    public Transaction create(Transaction transaction) {
        return context.insertInto(TransactionTable.TABLE)
                .set(TransactionTable.TABLE.DATE, LocalDate.now())
                .set(TransactionTable.TABLE.AMOUNT, transaction.getAmount())
                .set(TransactionTable.TABLE.TYPE, transaction.getType().name())
                .set(TransactionTable.TABLE.CATEGORY_ID, transaction.getCategoryId().intValue())
                .returning()
                .fetchOne()
                .into(Transaction.class);
    }

    public List<Transaction> getAllBetweenDates(LocalDate from, LocalDate to) {
        return context.select()
                .from(TransactionTable.TABLE)
                .where(TransactionTable.TABLE.DATE.between(from, to))
                .fetch()
                .into(Transaction.class);
    }

    public List<TransactionJoinCategory> getAllByCategoryId(Long id) {
        return context.select(TransactionTable.TABLE.ID,
                        TransactionTable.TABLE.AMOUNT,
                        TransactionTable.TABLE.TYPE,
                        TransactionTable.TABLE.DATE,
                        CategoryTable.TABLE.NAME,
                        CategoryTable.TABLE.TYPE
                )
                .from(TransactionTable.TABLE)
                .leftJoin(CategoryTable.TABLE)
                .on(CategoryTable.TABLE.ID.eq(TransactionTable.TABLE.CATEGORY_ID))
                .and(TransactionTable.TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch()
                .into(TransactionJoinCategory.class);
    }

    public List<TransactionJoinCategory> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to) {
        return context.select(TransactionTable.TABLE.ID,
                        TransactionTable.TABLE.AMOUNT,
                        TransactionTable.TABLE.TYPE,
                        TransactionTable.TABLE.DATE,
                        CategoryTable.TABLE.NAME,
                        CategoryTable.TABLE.TYPE
                )
                .from(TransactionTable.TABLE)
                .leftJoin(CategoryTable.TABLE)
                .on(CategoryTable.TABLE.ID.eq(TransactionTable.TABLE.CATEGORY_ID))
                .where(TransactionTable.TABLE.DATE.between(from, to))
                .and(TransactionTable.TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch()
                .into(TransactionJoinCategory.class);
    }

    public Double getBalance() {
        var balance = 0;
        context.select()
                .from(TransactionTable.TABLE)
                .fetch()
                .map(record -> record.into(TransactionTable.class));

        return null;
    }
}
