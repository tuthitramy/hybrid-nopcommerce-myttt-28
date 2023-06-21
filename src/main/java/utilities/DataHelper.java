package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale local = new Locale("en");
	private Faker faker = new Faker(local);

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getValidPassword() {
		return faker.internet().password(6, 12);
	}

	public String getInvalidPassword() {
		return faker.internet().password(1, 3);
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getCityName() {
		return faker.address().cityName();
	}

	public String getAddress1() {
		return faker.address().fullAddress();
	}

	public String getAddress2() {
		return faker.address().secondaryAddress();
	}

	public String getPostalCode() {
		return faker.address().zipCode();
	}

	public String getFaxNumber() {
		return faker.number().toString();
	}

	public String getRandomString() {
		return faker.toString();
	}

	public String getRandomCardNumber() {
		return faker.business().creditCardNumber();
	}

}
