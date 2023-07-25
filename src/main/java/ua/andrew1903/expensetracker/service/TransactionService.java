package ua.andrew1903.expensetracker.service;

import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.dto.TransactionJoinCategory;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transaction);

    List<TransactionDTO> getAllBetweenDates(LocalDate from, LocalDate to);

    List<TransactionJoinCategory> getAllByCategoryId(Long id);

    List<TransactionJoinCategory> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to);

    Double getBalance();
}
