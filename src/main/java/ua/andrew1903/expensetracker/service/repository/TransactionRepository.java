package ua.andrew1903.expensetracker.service.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ua.andrew1903.expensetracker.db.tables.CategoryTable;
import ua.andrew1903.expensetracker.db.tables.TransactionTable;
import ua.andrew1903.expensetracker.dto.GroupingResultDTO;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;
import java.util.List;

import static org.jooq.impl.DSL.*;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private static final CategoryTable CATEGORY_TABLE = CategoryTable.TABLE;
    private static final TransactionTable TRANSACTION_TABLE = TransactionTable.TABLE;

    private final DSLContext context;

    public Transaction create(Transaction transaction) {
        return context.insertInto(TRANSACTION_TABLE)
                .set(TRANSACTION_TABLE.DATE, LocalDate.now())
                .set(TRANSACTION_TABLE.AMOUNT, transaction.getAmount())
                .set(TRANSACTION_TABLE.TYPE, transaction.getTransactionType().name())
                .set(TRANSACTION_TABLE.CATEGORY_ID, transaction.getCategory().getId().intValue())
                .returning()
                .fetchOne(TransactionRepository::recordMapToTransaction);
    }

    public List<Transaction> getAllBetweenDates(LocalDate from, LocalDate to) {
        return context.select()
                .from(TRANSACTION_TABLE)
                .where(TRANSACTION_TABLE.DATE.between(from, to))
                .fetch(TransactionRepository::recordMapToTransaction);
    }

    public List<Transaction> getAllByCategoryId(Long id) {
        return context.select()
                .from(TRANSACTION_TABLE)
                .leftJoin(CATEGORY_TABLE)
                .on(CATEGORY_TABLE.ID.eq(TRANSACTION_TABLE.CATEGORY_ID))
                .where(TRANSACTION_TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch(TransactionRepository::recordMapToTransactionJoin);
    }

    public List<Transaction> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to) {
        return context.select()
                .from(TRANSACTION_TABLE)
                .leftJoin(CATEGORY_TABLE)
                .on(CATEGORY_TABLE.ID.eq(TRANSACTION_TABLE.CATEGORY_ID))
                .where(TRANSACTION_TABLE.DATE.between(from, to))
                .and(TRANSACTION_TABLE.CATEGORY_ID.eq(id.intValue()))
                .fetch(TransactionRepository::recordMapToTransactionJoin);
    }

    public Double getBalance() {
        return context.select(
                        sum(iif(TRANSACTION_TABLE.TYPE.eq(TransactionType.EXPENSE.name()),
                                (TRANSACTION_TABLE.AMOUNT).mul(-1), TRANSACTION_TABLE.AMOUNT))
                )
                .from(TRANSACTION_TABLE)
                .fetchOne()
                .into(Double.class);
    }

    public List<GroupingResultDTO> groupByDate() {
        return context.select(TRANSACTION_TABLE.DATE, TRANSACTION_TABLE.TYPE, sum(TRANSACTION_TABLE.AMOUNT).cast(Double.class))
                .from(TRANSACTION_TABLE)
                .groupBy(List.of(TRANSACTION_TABLE.TYPE, TRANSACTION_TABLE.DATE))
                .fetch()
                .into(GroupingResultDTO.class);
    }

    private static Transaction recordMapToTransaction(Record record) {
        return Transaction.builder()
                .id(record.get(TransactionTable.TABLE.ID).longValue())
                .amount(record.get(TransactionTable.TABLE.AMOUNT))
                .transactionType(TransactionType.valueOf(record.get(TransactionTable.TABLE.TYPE)))
                .date(record.get(TransactionTable.TABLE.DATE))
                .category(Category.builder()
                        .id(record.get(CATEGORY_TABLE.ID).longValue())
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
                        .name(record.get(CATEGORY_TABLE.NAME))
                        .type(CategoryType.valueOf(record.get(CATEGORY_TABLE.TYPE)))
                        .build())
                .build();
    }
}
