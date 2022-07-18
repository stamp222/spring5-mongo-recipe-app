package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() {
        Category category = new Category();
        category.setDescription("SuperCat");
        categoryReactiveRepository.save(category).then().block();
        Category superCat = categoryReactiveRepository.findByDescription("SuperCat").block();
        assertNotNull(superCat.getId());
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("SuperCat");
        categoryReactiveRepository.save(category).block();
        assertEquals(Long.valueOf(1L), categoryReactiveRepository.count().block());

    }
}