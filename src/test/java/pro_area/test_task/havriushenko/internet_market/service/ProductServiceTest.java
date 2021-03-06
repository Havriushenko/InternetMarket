package pro_area.test_task.havriushenko.internet_market.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.exception.ProductNotFoundException;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;
import pro_area.test_task.havriushenko.internet_market.repository.ProductGroupRerository;
import pro_area.test_task.havriushenko.internet_market.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.*;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(JUnit4.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductGroupRerository productGroupRerository;
    @Mock
    private ProductConverter productConverter;

    private ProductDto product1;
    private ProductDto product2;
    private ProductGroupDto productGroup;
    private List<ProductDto> products;
    private ProductModel productModel1;
    private ProductModel productModel2;
    private ProductGroupModel productGroupModel;
    private List<ProductModel> productModels;

    private ProductService tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productGroup = new ProductGroupDto(TEST_PRODUCT_GROUP_NAME);
        productGroupModel = new ProductGroupModel(TEST_PRODUCT_GROUP_MODEL_NAME);
        createProductDtos();
        createProductModels();

        tested = new ProductService(productConverter, productGroupRerository, productRepository);
    }

    private void createProductDtos() {
        product1 = new ProductDto();
        product1.setId(FIRST_TEST_ID);
        product1.setName(TEST_PRODUCT_NAME_1);
        product1.setPrice(TEST_PRODUCT_PRICE);
        product1.setDescription(TEST_PRODUCT_DESCRIPTION);
        product1.setGroup(productGroup);

        product2 = new ProductDto();
        product2.setId(SECOND_TEST_ID);
        product2.setName(TEST_PRODUCT_NAME_2);
        product2.setPrice(TEST_PRODUCT_PRICE);
        product2.setDescription(TEST_PRODUCT_DESCRIPTION);
        product2.setGroup(productGroup);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
    }

    private void createProductModels() {
        productModel1 = new ProductModel();
        productModel1.setId(FIRST_TEST_ID);
        productModel1.setName(TEST_PRODUCT_NAME_1);
        productModel1.setPrice(TEST_PRODUCT_PRICE);
        productModel1.setDescription(TEST_PRODUCT_DESCRIPTION);
        productModel1.setGroup(productGroupModel);

        productModel2 = new ProductModel();
        productModel2.setId(SECOND_TEST_ID);
        productModel2.setName(TEST_PRODUCT_NAME_2);
        productModel2.setPrice(TEST_PRODUCT_PRICE);
        productModel2.setDescription(TEST_PRODUCT_DESCRIPTION);
        productModel2.setGroup(productGroupModel);

        productModels = new ArrayList<>();
        productModels.add(productModel1);
        productModels.add(productModel2);
    }

    @Test
    public void throwExceptionWhenProductAlredyExists() {
        when(productRepository.findByName(TEST_PRODUCT_NAME_1)).thenReturn(Optional.ofNullable(productModel1));

        try {
            tested.addProduct(product1);
        } catch (IllegalArgumentException ex) {
            String result = ex.getMessage();
            assertEquals(MESSAGE_PRODUCT_IS_PRESENT_IN_DATA_BASE, result);
        }
    }

    @Test
    public void throwExceptionWhenProductDtoIsNull() {
        try {
            tested.addProduct(null);
        } catch (NullPointerException ex) {
            String result = ex.getMessage();
            assertEquals(MESSAGE_PRODUCT_IS_NULL, result);
        }
    }

    @Test
    public void createNewProduct() {
        when(productRepository.findByName(TEST_PRODUCT_NAME_2)).thenReturn(Optional.empty());
        when(productConverter.convertToModel(product2)).thenReturn(productModel2);
        when(productRepository.save(productModel2)).thenReturn(productModel2);

        tested.addProduct(product2);

        verify(productRepository).save(productModel2);
    }

    @Test
    public void throwExceptionWhenProductDoesNotExist() {
        when(productRepository.findById(NON_EXISTING_TEST_ID)).thenReturn(Optional.empty());

        try {
            tested.getProductById(NON_EXISTING_TEST_ID);
        } catch (ProductNotFoundException ex) {
            String result = ex.getMessage();
            assertEquals(MESSAGE_PRODUCT_NOT_FOUND_EXCEPTION, result);
        }
    }

    @Test
    public void findProductById() {
        when(productRepository.findById(SECOND_TEST_ID)).thenReturn(Optional.ofNullable(productModel2));
        when(productConverter.convertToDto(productModel2)).thenReturn(product2);

        ProductDto result = tested.getProductById(SECOND_TEST_ID);

        assertEquals(SECOND_TEST_ID, result.getId());
        assertEquals(TEST_PRODUCT_NAME_2, result.getName());
    }

    @Test
    public void findAllProducts() {
        when(productRepository.findAll()).thenReturn(productModels);
        when(productConverter.convertToDto(productModel1)).thenReturn(product1);
        when(productConverter.convertToDto(productModel2)).thenReturn(product2);

        List<ProductDto> result = tested.getProducts();

        assertEquals(2, result.size());
        assertEquals(product2, result.get(1));
        assertEquals(product1, result.get(0));
    }

    @Test
    public void throwExceptionWhenModifyNotExistingProduct() {
        when(productRepository.findById(FIRST_TEST_ID)).thenReturn(null);

        try {
            tested.editProduct(product2);
        } catch (IllegalArgumentException ex) {
            String result = ex.getMessage();
            assertEquals(MESSAGE_SUCH_PRODUCT_DOES_NOT_EXIST, result);
        }
    }

    @Test
    public void modifyAnExistingProductExpected() {
        when(productRepository.findById(SECOND_TEST_ID)).thenReturn(Optional.ofNullable(productModel2));
        when(productConverter.convertToModel(product2)).thenReturn(productModel2);

        tested.editProduct(product2);

        verify(productRepository).save(any());
    }

    @Test
    public void throwExceptionWhenDeleteNonExistProductAnd() {
        when(productRepository.findById(NON_EXISTING_TEST_ID)).thenReturn(Optional.empty());

        try {
            tested.deleteProduct(NON_EXISTING_TEST_ID);
        } catch (ProductNotFoundException ex) {
            String result = ex.getMessage();
            assertEquals(MESSAGE_PRODUCT_NOT_FOUND_EXCEPTION, result);
        }
    }
}