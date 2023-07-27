package ua.andrew1903.expensetracker.service.uploader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileManagerTest {
    @Test
    void givenOK_whenUploadData_thenSucceed(@Mock FileManager fileManager) {
        byte[] data = {123, 1, 5, 6, 6};
        when(fileManager.upload(eq(data))).thenReturn(HttpStatus.OK);
        fileManager.upload(data);
        verify(fileManager).upload(data);
    }

    @Test
    void givenOK_whenUploadNULL_thenFail(@Mock FileManager fileManager) {
        when(fileManager.upload(null)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> fileManager.upload(null), "RuntimeException was expected");
    }

    @Test
    void givenDROPBOX_whenGetStorage_thenSucceed(@Mock FileManager fileManager) {
        when(fileManager.getStorage()).thenReturn(Storage.DROPBOX);
        fileManager.getStorage();
        verify(fileManager).getStorage();
    }
}