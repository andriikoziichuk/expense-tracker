package ua.andrew1903.expensetracker.service.uploader;

import org.springframework.http.HttpStatus;

public interface FileManager {
    Storage getStorage();
    HttpStatus upload(byte[] bytes);
}
