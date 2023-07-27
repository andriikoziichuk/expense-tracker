package ua.andrew1903.expensetracker.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.service.mapper.impl.TransactionMapperImpl;
import ua.andrew1903.expensetracker.model.Transaction;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper MAPPER = new TransactionMapperImpl();

    Transaction toTransaction(TransactionDTO dto);

    @InheritInverseConfiguration
    TransactionDTO fromTransaction(Transaction transaction);

    List<Transaction> toTransactionList(List<TransactionDTO> dtos);

    List<TransactionDTO> fromTransactionList(List<Transaction> transactions);
}
