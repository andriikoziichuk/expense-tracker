package ua.andrew1903.expensetracker.service.csv;

import java.util.List;

public interface CSVSerializable {
    String[] getHeader();
    List<?> getCSVValues();
}
