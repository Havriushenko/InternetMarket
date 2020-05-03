package pro_area.test_task.havriushenko.internet_market.util;

public interface SecurityConstants {
    String SECURITY_SECRET_WORD = "TestJWTTokenSecurityWordForInternetMarket";
    long EXPERATION_TIME = 864_000_000;     //10 days
    String TOKEN_PREFIX = "Bearer ";   //подаватель,предъявитель
    String HEADER_STRING = "Authorization";
    String SING_UP_URL = "/users/sing-up";
    String GET_ALL_PRODUCTS_URL = "/product/getProducts";
    String GET_PRODUCT_BY_ID_URL = "/product/getProductById";
}
