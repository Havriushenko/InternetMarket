package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;

import static org.junit.Assert.*;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.FIRST_TEST_ID;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.TEST_PRODUCT_GROUP_NAME;

@RunWith(JUnit4.class)
public class ProductGroupConverterImplTest {

    private ProductGroupDto productGroupDto;
    private ProductGroupModel productGroupModel;

    private ProductGroupConverter tested;

    @Before
    public void setUp() throws Exception {
        createProductGroupDto();
        createProductGroupModel();

        tested = new ProductGroupConverterImpl();
    }

    private void createProductGroupDto() {
        productGroupDto = new ProductGroupDto();
        productGroupDto.setId(FIRST_TEST_ID);
        productGroupDto.setName(TEST_PRODUCT_GROUP_NAME);
    }

    private void createProductGroupModel() {
        productGroupModel = new ProductGroupModel();
        productGroupModel.setId(FIRST_TEST_ID);
        productGroupModel.setGroup(TEST_PRODUCT_GROUP_NAME);
    }

    @Test
    public void convertTestProductGroupDtoToModel() {
        ProductGroupModel result = tested.convertToModel(productGroupDto);

        assertEquals(productGroupDto.getName(), result.getName());
        assertEquals(productGroupModel, result);
    }

    @Test
    public void convertTestProductGroupModelToDto() {
        ProductGroupDto result = tested.convertToDto(productGroupModel);

        assertEquals(productGroupModel.getName(), result.getName());
        assertEquals(productGroupDto, result);
    }
}