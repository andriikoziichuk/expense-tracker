package ua.andrew1903.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.dto.TransactionJoinCategory;
import ua.andrew1903.expensetracker.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final TransactionService service;

    @PostMapping("/api/expenses")
    public Map<String, TransactionDTO> create(@RequestBody TransactionDTO transaction) {
        return Map.of(
                "created",
                service.create(transaction)
        );
    }

    @GetMapping("/api/expenses/range")
    public Map<String, List<TransactionDTO>> betweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return Map.of(
                "between " + from + " and " + to,
                service.getAllBetweenDates(from, to)
        );
    }

    @GetMapping("/api/categories/{id}/expenses")
    public Map<Long, List<TransactionJoinCategory>> getAllByCategoryId(@PathVariable Long id) {
        return Map.of(
                id,
                service.getAllByCategoryId(id)
        );
    }

    @GetMapping("/api/categories/{id}/expenses/range")
    public Map<String, List<TransactionJoinCategory>> getAllByCategoryIdAndBetweenDates(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return Map.of(
                "between " + from + " and " + to + " by id#" + id,
                service.getAllByCategoryIdAndBetweenDates(id, from, to)
        );
    }

    @GetMapping("/api/current-balance")
    public Map<String, Double> getCurrentBalance() {
        return Map.of(
                "balance", service.getBalance()
        );
    }
}
