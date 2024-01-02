package ua.andrew1903.expensetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.dto.GroupingResultDTO;
import ua.andrew1903.expensetracker.dto.StatementDTO;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.dto.TransactionKafkaDTO;
import ua.andrew1903.expensetracker.kafka.Topics;
import ua.andrew1903.expensetracker.model.Transaction;
import ua.andrew1903.expensetracker.service.mapper.TransactionMapper;
import ua.andrew1903.expensetracker.service.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionMapper mapper = TransactionMapper.MAPPER;

    private final TransactionRepository repository;

    private final Topics topics;
    private final KafkaTemplate<String, TransactionKafkaDTO> template;

    public TransactionDTO create(TransactionDTO transaction) {
        Transaction response = repository.create(mapper.toTransaction(transaction));
        return mapper.fromTransaction(response);
    }

    public TransactionDTO createAndSendMessage(TransactionDTO transaction) {
        Transaction response = repository.create(mapper.toTransaction(transaction));
        TransactionKafkaDTO kafkaDTO = TransactionKafkaDTO.builder()
                .date(response.getDate())
                .amount(response.getAmount())
                .transactionType(response.getTransactionType())
                .build();

        template.send(topics.expenseTrackerTopic(), kafkaDTO);
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

    public List<GroupingResultDTO> groupByDate() {
        return repository.groupByDate();
    }
}
