package ua.andrew1903.expensetracker.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {
    @Value("${access.token}")
    private String ACCESS_TOKEN;

    @Bean
    public DbxClientV2 dropboxClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("ua.andrew1903.expense-tracker").build();
        return new DbxClientV2(config, ACCESS_TOKEN);
    }
}
