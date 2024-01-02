package ua.andrew1903.expensetracker.service.mapper.impl;

import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Test;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.model.CategoryType;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.model.TransactionType;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.jooq.lambda.Seq.seq;

class TransactionMapperImplTest {
    private final TransactionMapperImpl underTest = new TransactionMapperImpl();
    private final Category category = new Category(2L, "Category2", CategoryType.ACTIVITIES);


    @Test
    void toTransaction() {
        var transactionDto = TransactionDTO.builder()
                .id(1L)
                .amount(1000)
                .date(LocalDate.now())
                .categoryId(category.getId())
                .categoryType(category.getType())
                .transactionType(TransactionType.REVENUE)
                .build();
        var result = underTest.toTransaction(transactionDto);
        var expected = new Transaction(1L, LocalDate.now(), 1000, TransactionType.REVENUE, category);

        assertTransaction(result, expected);
    }

    @Test
    void fromTransaction() {
        var transaction = new Transaction(4214L, LocalDate.now(), 543523252, TransactionType.REVENUE, category);
        var result = underTest.fromTransaction(transaction);
        var expected = TransactionDTO.builder()
                .id(4214L)
                .amount(543523252)
                .date(LocalDate.now())
                .categoryId(category.getId())
                .categoryType(category.getType())
                .transactionType(TransactionType.REVENUE)
                .build();

        assertTransactionDto(result, expected);
    }

    @Test
    void toTransactionList() {
        var transactionDtos = List.of(
                TransactionDTO.builder()
                        .id(1L)
                        .amount(1000)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.REVENUE)
                        .build(),
                TransactionDTO.builder()
                        .id(2L)
                        .amount(12431)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.EXPENSE)
                        .build(),
                TransactionDTO.builder()
                        .id(3L)
                        .amount(4325)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.REVENUE)
                        .build());

        var resultList = underTest.toTransactionList(transactionDtos);

        var expected = List.of(
                new Transaction(1L, LocalDate.now(), 1000, TransactionType.REVENUE, category),
                new Transaction(2L, LocalDate.now(), 12431, TransactionType.EXPENSE, category),
                new Transaction(3L, LocalDate.now(), 4325, TransactionType.REVENUE, category));

        Seq.zip(seq(resultList), seq(expected))
                .forEach(tuple -> assertTransaction(tuple.v1, tuple.v2));
    }

    @Test
    void fromTransactionList() {
        var categoryDtos = List.of(
                TransactionDTO.builder()
                        .id(1L)
                        .amount(1000)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.REVENUE)
                        .build(),
                TransactionDTO.builder()
                        .id(2L)
                        .amount(12431)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.EXPENSE)
                        .build(),
                TransactionDTO.builder()
                        .id(3L)
                        .amount(4325)
                        .date(LocalDate.now())
                        .categoryId(category.getId())
                        .categoryType(category.getType())
                        .transactionType(TransactionType.REVENUE)
                        .build());

        var resultList = underTest.toTransactionList(categoryDtos);

        var expected = List.of(
                new Transaction(1L, LocalDate.now(), 1000, TransactionType.REVENUE, category),
                new Transaction(2L, LocalDate.now(), 12431, TransactionType.EXPENSE, category),
                new Transaction(3L, LocalDate.now(), 4325, TransactionType.REVENUE, category));

        Seq.zip(seq(resultList), seq(expected))
                .forEach(tuple -> assertTransaction(tuple.v1, tuple.v2));
    }

    private void assertTransaction(Transaction actual, Transaction expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getDate(), is(expected.getDate()));
        assertThat(actual.getAmount(), is(expected.getAmount()));
        assertThat(actual.getCategory(), is(expected.getCategory()));
        assertThat(actual.getTransactionType(), is(expected.getTransactionType()));
    }

    private void assertTransactionDto(TransactionDTO actual, TransactionDTO expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getDate(), is(expected.getDate()));
        assertThat(actual.getAmount(), is(expected.getAmount()));
        assertThat(actual.getCategoryId(), is(expected.getCategoryId()));
        assertThat(actual.getCategoryType(), is(expected.getCategoryType()));
        assertThat(actual.getTransactionType(), is(expected.getTransactionType()));
    }
}