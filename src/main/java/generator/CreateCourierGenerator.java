package generator;

import pojo.CreateCourier;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateCourierGenerator {

    public static CreateCourier getRandomNewCourierGenerator() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }

    public static CreateCourier getNewCourierWithFirstNameNull() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(null);
        return createCourier;
    }

    public static CreateCourier getNewCourierWithLoginNull() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(null);
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }

    public static CreateCourier getNewCourierWithPasswordNull() {
        CreateCourier createCourier = new CreateCourier();
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourier.setPassword(null);
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourier;
    }
}