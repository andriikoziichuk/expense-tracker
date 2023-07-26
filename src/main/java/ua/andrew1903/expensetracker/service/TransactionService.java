package ua.andrew1903.expensetracker.service;

import ua.andrew1903.expensetracker.dto.StatementDTO;
import ua.andrew1903.expensetracker.dto.TransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transaction);

    List<TransactionDTO> getAllBetweenDates(LocalDate from, LocalDate to);

    List<TransactionDTO> getAllByCategoryId(Long id);

    List<TransactionDTO> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to);

    StatementDTO getBalance();
}
