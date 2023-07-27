package ua.andrew1903.expensetracker.cucumber.test;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import ua.andrew1903.expensetracker.cucumber.commons.HttpClient;
import ua.andrew1903.expensetracker.dto.CategoryDTO;
import ua.andrew1903.expensetracker.model.CategoryType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("all")
public class CategoryCucumberStepDefinitions {
    private final List<CategoryType> types = Arrays.stream(CategoryType.values()).toList();

    @Autowired
    private HttpClient client;

    @When("^I create one category$")
    public void i_create_one_categories() {
        generateData(1).forEach(category -> client.create(category));
    }

    @Then("^There is (\\d+) category in the db$")
    public void db_should_contain_only_one_category(int number) {
        assertThat(client.getAll().size()).isEqualTo(number);
    }

    @When("^I create (\\d+) categories$")
    public void i_create_some_categories(int quantity) {
        generateData(quantity).forEach(category -> client.create(category));
    }

    @Then("^There are (\\d+) categories in the db$")
    public void db_should_contain_four_categories(int number) {
        assertThat(client.getAll().size()).isEqualTo(number);
    }

    @When("^I get category by id (\\d+)$")
    public void i_pull_category_by_id(Long id) {
        client.getById(id);
    }

    @Then("^Controller should return category by id (\\d+)$")
    public void controller_should_return_category(Long id) {
        assertThat(client.getById(id)).isInstanceOf(CategoryDTO.class);
    }

    private List<CategoryDTO> generateData(int quantity) {
        var randomType = types.get(ThreadLocalRandom.current().nextInt(0, types.size()));

        return Collections.nCopies(quantity,
                CategoryDTO.builder()
                        .name(randomType.name())
                        .type(randomType)
                        .build());
    }
}
