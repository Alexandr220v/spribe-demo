package api;


import config.ConfigLoader;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class BaseApi {
    protected static final String BASE_URL = ConfigLoader.getProperty("app.baseUrl");


}
