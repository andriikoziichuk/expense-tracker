package ua.andrew1903.expensetracker.cucumber.commons;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.andrew1903.expensetracker.dto.CategoryDTO;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
@SuppressWarnings("all")
public class HttpClient {
    private final String SERVER_URL = "http://localhost";
    private final String REQUEST_MAPPING = "/api/categories";
    private final String ENDPOINT = "";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String categoriesEndpoint() {
        return SERVER_URL + ":" + port + REQUEST_MAPPING + ENDPOINT;
    }

    public HttpStatusCode create(CategoryDTO categoryDTO) {
        return restTemplate.postForEntity(categoriesEndpoint(), categoryDTO, CategoryDTO.class).getStatusCode();
    }

    public List<?> getAll() {
        return restTemplate.getForEntity(categoriesEndpoint(), List.class).getBody();
    }

    public CategoryDTO getById(Long id) {
        return restTemplate.getForEntity(categoriesEndpoint(), CategoryDTO.class).getBody();
    }
}
