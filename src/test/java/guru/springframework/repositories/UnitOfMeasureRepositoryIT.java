package guru.springframework.repositories;

import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by jt on 6/17/17.
 */
@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository UnitOfMeasureRepository;

    @Autowired
    CategoryRepository CategoryRepository;

    @Autowired
    RecipeRepository RecipeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        RecipeRepository.deleteAll();
        CategoryRepository.deleteAll();
        UnitOfMeasureRepository.deleteAll();

        RecipeBootstrap bootstrap = new RecipeBootstrap(CategoryRepository, RecipeRepository, UnitOfMeasureRepository);
        bootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() throws Exception {

        Optional<UnitOfMeasure> uomOptional = UnitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {

        Optional<UnitOfMeasure> uomOptional = UnitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", uomOptional.get().getDescription());
    }

}