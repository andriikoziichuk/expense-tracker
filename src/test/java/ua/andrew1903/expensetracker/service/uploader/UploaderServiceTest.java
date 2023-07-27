package ua.andrew1903.expensetracker.service.uploader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ua.andrew1903.expensetracker.controller.TransactionController;
import ua.andrew1903.expensetracker.dto.TransactionDTO;
import ua.andrew1903.expensetracker.model.TransactionType;
import ua.andrew1903.expensetracker.service.csv.CSVConverter;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploaderServiceTest {
    private final Random random = new Random();


    private TransactionController transactionController;
    @InjectMocks
    private UploaderService uploaderService;
    @Mock
    private FileManager dropboxFileManager;
    @Spy
    private CSVConverter converter;
    @Spy
    private ArrayList<FileManager> fileManagers;

    @BeforeEach
    void setup() {
        fileManagers.add(dropboxFileManager);
//        = List.of(dropboxFileManager);
//        uploaderService = new UploaderService(fileManagers, converter);
        transactionController = new TransactionController(null, uploaderService);

    }

    @Test
    void throwsNoSuckElement_whenUploadNULL_thenSucceed() {
        assertThrows(NoSuchElementException.class, () -> uploaderService.upload(null, Storage.DROPBOX));
    }

    @Test
    void givenOk_whenUploadEmptyCollection_thenSucceed() {
        when(dropboxFileManager.getStorage()).thenReturn(Storage.DROPBOX);
        when(dropboxFileManager.upload(any())).thenReturn(HttpStatus.OK);
        assertEquals(HttpStatus.OK, uploaderService.upload(Collections.emptyList(), Storage.DROPBOX));
    }

    @Test
    void throwsRuntime_whenUploadToNonexistent_thenFail() {
        assertThrows(RuntimeException.class, () -> uploaderService.upload(any(List.class), any(Storage.class)));
    }

    private List<TransactionDTO> generateData(int amount) {

        return Collections.nCopies(amount,
                TransactionDTO.builder()
                        .id(random.nextLong())
                        .amount(random.nextDouble())
                        .transactionType(TransactionType.EXPENSE)
                        .date(LocalDate.now())
                        .build());
    }
}