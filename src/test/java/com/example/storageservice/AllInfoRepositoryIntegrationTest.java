package com.example.storageservice;

import com.example.storageservice.allProducts.AllProductsRepository;
import com.example.storageservice.allProducts.ProductAllInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AllInfoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    AllProductsRepository repository;

    @Test
    public void shouldAddProductsToDBTest() {
        UUID testItemId = UUID.randomUUID();

        ProductAllInfo newInfo = new ProductAllInfo(testItemId, "nice name", "nice description",
                "nice material", "nice color", "heavy", 30.0, 35.7,
                753642L, 5, "Berlin");
        ProductAllInfo info = repository.save(newInfo);
        assertThat(info).hasFieldOrPropertyWithValue("name", "nice name");
        assertThat(info).hasFieldOrPropertyWithValue("description", "nice description");
        assertThat(info).hasFieldOrPropertyWithValue("material", "nice material");
        assertThat(info).hasFieldOrPropertyWithValue("colour", "nice color");
        assertThat(info).hasFieldOrPropertyWithValue("weight", "heavy");
        assertThat(info).hasFieldOrPropertyWithValue("priceWithoutMwSt", 30.0);
        assertThat(info).hasFieldOrPropertyWithValue("priceWithMwSt", 35.7);
        assertThat(info).hasFieldOrPropertyWithValue("deliveryTime", 753642L);
        assertThat(info).hasFieldOrPropertyWithValue("amount", 5);
        assertThat(info).hasFieldOrPropertyWithValue("location", "Berlin");
    }
}
