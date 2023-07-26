package ua.andrew1903.expensetracker.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ua.andrew1903.expensetracker.db.tables.CategoryTable;
import ua.andrew1903.expensetracker.db.tables.TransactionTable;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;
import java.util.List;

import static org.jooq.impl.DSL.iif;
import static org.jooq.impl.DSL.sum;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final DSLContext context;

    public Transaction create(Transaction transaction) {
        return context.insertInto(TransactionTable.TABLE)
                .set(TransactionTable.TABLE.DATE, LocalDate.now())
                .set(TransactionTable.TABLE.AMOUNT, transaction.getAmount())
                .set(TransactionTable.TABLE.TYPE, transaction.getTransactionType().name())
                .set(TransactionTable.TABLE.CATEGORY_ID, transaction.getCategory().getId().intValue())
                .returning()
                .fetchOne(TransactionRepository::recordMapToTransaction);
    }

    public List<Transaction> getAllBetweenDates(LocalDate from, LocalDate to) {
        return context.select()
                .from(TransactionTable.TABLE)
                .where(TransactionTable.TABLE.DATE.between(from, to))
                .fetch(TransactionRepository::recordMapToTransaction);
    }

    public List<Transaction> getAllByCategoryId(Long id) {
        return context.select()
                .from(TransactionTable.TABLE)
                .leftJoin(CategoryTable.TABLE)
                .on(CategoryTable.TABLE.ID.eq(TransactionTable.TABLE.CATEGORY_ID))
                .where(TransactionTable.TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch(TransactionRepository::recordMapToTransactionJoin);
    }

    public List<Transaction> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to) {
        return context.select()
                .from(TransactionTable.TABLE)
                .leftJoin(CategoryTable.TABLE)
                .on(CategoryTable.TABLE.ID.eq(TransactionTable.TABLE.CATEGORY_ID))
                .where(TransactionTable.TABLE.DATE.between(from, to))
                .and(TransactionTable.TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch(TransactionRepository::recordMapToTransactionJoin);
    }

    public Double getBalance() {
        return context.select(
                        sum(iif(TransactionTable.TABLE.TYPE.eq(TransactionType.EXPENSE.name()),
                                (TransactionTable.TABLE.AMOUNT).mul(-1), TransactionTable.TABLE.AMOUNT))
                )
                .from(TransactionTable.TABLE)
                .fetchOne()
                .into(Double.class);
    }

    private static Transaction recordMapToTransaction(Record record) {
        return Transaction.builder()
                .id(record.get(TransactionTable.TABLE.ID).longValue())
                .amount(record.get(TransactionTable.TABLE.AMOUNT))
                .transactionType(TransactionType.valueOf(record.get(TransactionTable.TABLE.TYPE)))
                .date(record.get(TransactionTable.TABLE.DATE))
                .category(Category.builder()
                        .id(record.get(CategoryTable.TABLE.ID).longValue())
                        .build())
                .build();
    }

    private static Transaction recordMapToTransactionJoin(Record record) {
        return Transaction.builder()
                .id(record.get(TransactionTable.TABLE.ID).longValue())
                .amount(record.get(TransactionTable.TABLE.AMOUNT))
                .transactionType(TransactionType.valueOf(record.get(TransactionTable.TABLE.TYPE)))
                .date(record.get(TransactionTable.TABLE.DATE))
                .category(Category.builder()
                        .name(record.get(CategoryTable.TABLE.NAME))
                        .type(CategoryType.valueOf(record.get(CategoryTable.TABLE.TYPE)))
                        .build())
                .build();
    }
}
