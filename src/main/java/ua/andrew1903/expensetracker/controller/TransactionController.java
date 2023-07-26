package ua.andrew1903.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.andrew1903.expensetracker.dto.StatementDTO;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/api/expenses")
    public TransactionDTO create(@RequestBody TransactionDTO transaction) {
        return service.create(transaction);
    }

    @GetMapping("/api/expenses/range")
    public List<TransactionDTO> betweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return service.getAllBetweenDates(from, to);
    }

    @GetMapping("/api/categories/{id}/expenses")
    public List<TransactionDTO> getAllByCategoryId(@PathVariable Long id) {
        return service.getAllByCategoryId(id);
    }

    @GetMapping("/api/categories/{id}/expenses/range")
    public List<TransactionDTO> getAllByCategoryIdAndBetweenDates(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return service.getAllByCategoryIdAndBetweenDates(id, from, to);
    }

    @GetMapping("/api/current-balance")
    public StatementDTO getCurrentBalance() {
        return service.getBalance();
    }
}
