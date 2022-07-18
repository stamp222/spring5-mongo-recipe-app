package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String SUPER_UOM = "SuperUOM";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void findByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(SUPER_UOM);
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        UnitOfMeasure superUOM = unitOfMeasureReactiveRepository.findByDescription(SUPER_UOM).block();
        assertNotNull(superUOM.getId());
    }

    @Test
    public void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(SUPER_UOM);
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();
        assertEquals(Long.valueOf(1L), unitOfMeasureReactiveRepository.count().block());

    }
}