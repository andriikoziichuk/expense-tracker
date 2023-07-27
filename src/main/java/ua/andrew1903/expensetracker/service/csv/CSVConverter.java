package ua.andrew1903.expensetracker.service.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CSVConverter {
    public <T extends CSVSerializable> byte[] convertToByteArray(List<T> transactions){
        if (Optional.ofNullable(transactions).isEmpty()) {
            throw new NoSuchElementException();
        }
        try (var output = new ByteArrayOutputStream();
             var printer = new CSVPrinter(new OutputStreamWriter(output), CSVFormat.DEFAULT
                     .withHeader(transactions.stream().findFirst().map(T::getHeader).orElse(new String[]{})))) {

            for (T tr : transactions) {
                printer.printRecord(tr.getCSVValues());
            }
            printer.close(true);

            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
