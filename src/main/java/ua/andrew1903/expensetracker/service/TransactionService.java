package ua.andrew1903.expensetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.dto.StatementDTO;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.service.mapper.TransactionMapper;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.service.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionMapper mapper = TransactionMapper.MAPPER;

    private final TransactionRepository repository;

    public TransactionDTO create(TransactionDTO transaction) {
        Transaction response = repository.create(mapper.toTransaction(transaction));
        return mapper.fromTransaction(response);
    }

    public List<TransactionDTO> getAllBetweenDates(LocalDate from, LocalDate to) {
        List<Transaction> response = repository.getAllBetweenDates(from, to);
        return mapper.fromTransactionList(response);
    }

    public List<TransactionDTO> getAllByCategoryId(Long id) {
        List<Transaction> response = repository.getAllByCategoryId(id);
        return mapper.fromTransactionList(response);
    }

    public List<TransactionDTO> getAllByCategoryIdAndBetweenDates(Long id, LocalDate from, LocalDate to) {
        List<Transaction> response = repository.getAllByCategoryIdAndBetweenDates(id, from, to);
        return mapper.fromTransactionList(response);
    }

    public StatementDTO getBalance() {
        return StatementDTO.builder()
                .balance(repository.getBalance())
                .build();
    }
}
