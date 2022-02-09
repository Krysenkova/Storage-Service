package com.example.storageservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.storageservice.storage.Storage;
import com.example.storageservice.storage.StorageRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

@DataJpaTest
public class StorageRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StorageRepository repository;

    private UUID testItemId_1 = UUID.randomUUID();
    private UUID testItemId_2 = UUID.randomUUID();
    private UUID testItemId_3 = UUID.randomUUID();

    @Test
    public void shouldFindNoStorageIfEmptyTest() {
        Iterable<Storage> storage = repository.findAll();
        assertThat(storage).isEmpty();
    }

    @Test
    public void shouldFindAllStorageTest() {


        Storage s1 = new Storage(testItemId_1, 5464769L, 5, "Berlin");
        entityManager.persist(s1);
        Storage s2 = new Storage(testItemId_2, 5466769L, 3, "London");
        entityManager.persist(s2);
        Storage s3 = new Storage(testItemId_3, 5464369L, 2, "Rome");
        entityManager.persist(s3);
        Iterable<Storage> storage = repository.findAll();
        assertThat(storage).hasSize(3).contains(s1, s2, s3);
    }

    @Test
    public void shouldFindStorageById() {
        Storage s1 = new Storage(testItemId_1, 5464769L, 5, "Berlin");
        entityManager.persist(s1);
        Storage s2 = new Storage(testItemId_2, 5466769L, 3, "London");
        entityManager.persist(s2);
        Storage foundStorage = repository.findById(s2.getItemId()).get();
        assertThat(foundStorage).isEqualTo(s2);
    }
}
