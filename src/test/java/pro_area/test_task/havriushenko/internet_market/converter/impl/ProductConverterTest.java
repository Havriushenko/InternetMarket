package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(JUnit4.class)
public class ProductConverterTest {

    @Mock
    private ProductGroupConverter productGroupConverter;

    private ProductDto product1;
    private ProductDto product2;
    private ProductGroupDto productGroup;
    private ProductModel productModel1;
    private ProductModel productModel2;
    private ProductGroupModel productGroupModel;


    private ProductConverterImpl tested;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        productGroup = new ProductGroupDto(TEST_PRODUCT_GROUP_NAME);
        productGroupModel = new ProductGroupModel(TEST_PRODUCT_GROUP_MODEL_NAME);
        createProductDtos();
        createProductModels();

        tested = new ProductConverterImpl(productGroupConverter);
    }

    private void createProductDtos() {
        product1 = new ProductDto();
        product1.setId(FIRST_TEST_PRODUCT_ID);
        product1.setName(TEST_PRODUCT_NAME_1);
        product1.setPrice(TEST_PRODUCT_PRICE);
        product1.setDescription(TEST_PRODUCT_DESCRIPTION);
        product1.setGroup(productGroup);

        product2 = new ProductDto();
        product2.setId(SECOND_TEST_PRODUCT_ID);
        product2.setName(TEST_PRODUCT_NAME_2);
        product2.setPrice(TEST_PRODUCT_PRICE);
        product2.setDescription(TEST_PRODUCT_DESCRIPTION);
        product2.setGroup(productGroup);
    }

    private void createProductModels() {
        productModel1 = new ProductModel();
        productModel1.setId(FIRST_TEST_PRODUCT_ID);
        productModel1.setName(TEST_PRODUCT_NAME_1);
        productModel1.setPrice(TEST_PRODUCT_PRICE);
        productModel1.setDescription(TEST_PRODUCT_DESCRIPTION);
        productModel1.setGroup(productGroupModel);

        productModel2 = new ProductModel();
        productModel2.setId(SECOND_TEST_PRODUCT_ID);
        productModel2.setName(TEST_PRODUCT_NAME_2);
        productModel2.setPrice(TEST_PRODUCT_PRICE);
        productModel2.setDescription(TEST_PRODUCT_DESCRIPTION);
        productModel2.setGroup(productGroupModel);
    }

    @Test
    public void convertProductWithIdOneToModelExpectedProductModelWithIdOne() {
        when(productGroupConverter.convertToModel(productGroup)).thenReturn(productGroupModel);

        ProductModel result = tested.convertToModel(product1);

        assertEquals(productModel1.getId(), result.getId());
        assertTrue(productModel1.equals(result));
    }

    @Test
    public void convertProductModelWithIdTwoToDtoExpectedProductDtoWithIdTwo() {
        when(productGroupConverter.convertToDto(productGroupModel)).thenReturn(productGroup);

        ProductDto result = tested.convertToDto(productModel1);

        assertEquals(product1.getId(), result.getId());
        assertTrue(product1.equals(result));
    }
}