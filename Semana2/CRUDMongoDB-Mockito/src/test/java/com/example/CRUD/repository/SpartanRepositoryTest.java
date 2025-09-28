package com.example.CRUD.repository;

import com.example.CRUD.model.Spartan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class SpartanRepositoryTest {

    @Autowired
    private SpartanRepository repository;

    @Test
    void testSaveAndFind() {
        Spartan spartan = new Spartan("Spartan-117", "MasterChief", "Blue Team", 50);
        repository.save(spartan);

        Spartan found = repository.findById(spartan.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getGamertag()).isEqualTo("MasterChief");
    }
}
