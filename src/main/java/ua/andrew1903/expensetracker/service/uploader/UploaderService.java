package ua.andrew1903.expensetracker.service.uploader;

import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Seq;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.service.csv.CSVConverter;
import ua.andrew1903.expensetracker.service.csv.CSVSerializable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UploaderService {
    private final List<FileManager> fileManagers;
    private final CSVConverter csvConverter;

    public <T extends CSVSerializable> HttpStatus upload(List<T> transactions, Storage uploadTo) {
        var data = csvConverter.convertToByteArray(transactions);

        FileManager fileManager = Seq.seq(fileManagers)
                .findFirst(uploader -> uploader.getStorage() == uploadTo)
                .orElseThrow(RuntimeException::new);

        return fileManager.upload(data);
    }
}
