package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String SUPER_UOM = "SuperUOM";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
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