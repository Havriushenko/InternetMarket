package pro_area.test_task.havriushenko.internet_market.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    private final String REST_PRODUCT = "/product";
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private ProductDto product1;
    private ProductDto product2;
    private ProductGroupDto productGroup;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productGroup = new ProductGroupDto(TEST_PRODUCT_GROUP_NAME);

        product1 = new ProductDto();
        product1.setName(TEST_PRODUCT_NAME_1);
        product1.setPrice(TEST_PRODUCT_PRICE);
        product1.setDescription(TEST_PRODUCT_DESCRIPTION);
        product1.setGroup(productGroup);

        product2 = new ProductDto();
        product2.setName(TEST_PRODUCT_NAME_2);
        product2.setPrice(TEST_PRODUCT_PRICE);
        product2.setDescription(TEST_PRODUCT_DESCRIPTION);
        product2.setGroup(productGroup);
    }

    @Test
    public void getAllProducts() throws Exception {
        this.mockMvc.perform(get(REST_PRODUCT + "/getProducts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)\",\n" +
                        "        \"price\": 70.0,\n" +
                        "        \"description\": \"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.\",\n" +
                        "        \"group\": {\n" +
                        "            \"id\": 1,\n" +
                        "            \"name\": \"Masks\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void findProductById() throws Exception {
        this.mockMvc.perform(get(REST_PRODUCT + "/getProductById?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)\",\n" +
                        "    \"price\": 70.0,\n" +
                        "    \"description\": \"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.\",\n" +
                        "    \"group\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Masks\"\n" +
                        "    }\n" +
                        "}"));
    }

    @Test
    public void createTestProduct() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(product1);

        mockMvc.perform(post(REST_PRODUCT + "/create").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }
}