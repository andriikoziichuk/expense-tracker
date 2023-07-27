package ua.andrew1903.expensetracker.service.uploader.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.andrew1903.expensetracker.service.TransactionService;
import ua.andrew1903.expensetracker.service.uploader.FileManager;
import ua.andrew1903.expensetracker.service.uploader.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class DropboxFileManager implements FileManager {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final DbxClientV2 client;

    @Override
    public Storage getStorage() {
        return Storage.DROPBOX;
    }

    @Override
    public HttpStatus upload(byte[] bytes) {
        try {
            uploadFile(client, bytes);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    public ListFolderResult getFiles(DbxClientV2 client) throws DbxException {
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        return result;
    }

    private void uploadFile(DbxClientV2 client, byte[] data) throws IOException {
        var tmpFile = File.createTempFile("tmp", ".csv", new File("/tmp"));
        Files.write(tmpFile.toPath(), data);

        try (InputStream in = new FileInputStream(tmpFile)) {
            client.files().uploadBuilder("/" + tmpFile.getName())
                    .uploadAndFinish(in);
        } catch (DbxException e) {
            logger.error(e.getMessage());
        }
    }
}
