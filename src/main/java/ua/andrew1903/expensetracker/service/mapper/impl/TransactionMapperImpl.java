package ua.andrew1903.expensetracker.service.mapper.impl;

import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.service.mapper.TransactionMapper;
import ua.andrew1903.expensetracker.model.Category;
import ua.andrew1903.expensetracker.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction toTransaction(TransactionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.id( dto.getId() );
        transaction.date( dto.getDate() );
        transaction.amount( dto.getAmount() );
        transaction.transactionType( dto.getTransactionType() );
        transaction.category( Category.builder()
                        .id(dto.getCategoryId())
                        .build()
        );

        return transaction.build();
    }

    @Override
    public TransactionDTO fromTransaction(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO.TransactionDTOBuilder transactionDTO = TransactionDTO.builder();

        transactionDTO.id( transaction.getId() );
        transactionDTO.amount( transaction.getAmount() );
        transactionDTO.transactionType( transaction.getTransactionType() );
        transactionDTO.date( transaction.getDate() );
        transactionDTO.categoryId(transaction.getCategory().getId() );

        return transactionDTO.build();
    }

    @Override
    public List<Transaction> toTransactionList(List<TransactionDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Transaction> list = new ArrayList<>( dtos.size() );
        for ( TransactionDTO transactionDTO : dtos ) {
            list.add( toTransaction( transactionDTO ) );
        }

        return list;
    }

    @Override
    public List<TransactionDTO> fromTransactionList(List<Transaction> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDTO> list = new ArrayList<>( transactions.size() );
        for ( Transaction transaction : transactions ) {
            list.add( fromTransaction( transaction ) );
        }

        return list;
    }
}
