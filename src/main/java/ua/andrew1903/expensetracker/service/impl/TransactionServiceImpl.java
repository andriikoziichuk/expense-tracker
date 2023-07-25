package ua.andrew1903.expensetracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.dto.TransactionJoinCategory;
import ua.andrew1903.expensetracker.mapper.TransactionMapper;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.repository.TransactionRepository;
import ua.andrew1903.expensetracker.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionMapper mapper = TransactionMapper.MAPPER;

    private final TransactionRepository repository;

    @Override
    public TransactionDTO create(TransactionDTO transaction) {
        Transaction response = repository.create(mapper.toTransaction(transaction));
        return mapper.fromTransaction(response);
    }

    @Override
    public List<TransactionDTO> getAllBetweenDates(LocalDate from, LocalDate to) {
        List<Transaction> response = repository.getAllBetweenDates(from, to);
        return mapper.fromTransactionList(response);
    }

    @Override
    public List<TransactionJoinCategory> getAllByCategoryId(Long id) {
//        List<Transaction> response = repository.getAllByCategoryId(id);
//        return mapper.fromTransactionList(response);
        return repository.getAllByCategoryId(id);
    }

    @Override
    public List<TransactionJoinCategory> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to) {
//        List<Transaction> response = repository.getAllByCategoryIdAndBetweenDates(id, from, to);
//        return mapper.fromTransactionList(response);
        return repository.getAllByCategoryIdAndBetweenDates(id, from, to);
    }

    @Override
    public Double getBalance() {
        return repository.getBalance();
    }
}
