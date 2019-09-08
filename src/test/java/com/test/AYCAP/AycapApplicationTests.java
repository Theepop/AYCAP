package com.test.AYCAP;

import com.test.AYCAP.Entity.Customer;
import com.test.AYCAP.repository.CustomerRepository;
import com.test.AYCAP.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AycapApplicationTests {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;

	private final String PLATINUM = "Platinum";
	private final String GOLD = "Gold";
	private final String SILVER = "Silver";




	@Test
	public void getUser() {
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"201909076884",
				"Platinum",
				57000);
		when(repository.findByUsername("famtest")).thenReturn(customer);
		assertEquals(customer, service.getCustomer("famtest"));
	}

	//	o Platinum (salary > 50,000 baht)
//	o Gold (salary between 30,000 to 50,000)
//	o Silver (salary < 30,000)
//- Reject if salary < 15,000 with error code (please define error code)



	@Test
	public void addUserPLATINUM() throws Exception {
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"",
				"",
				57000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(PLATINUM, service.addNewCustomer(customer).getMemberType());
	}
	@Test
	public void addUserGOLD() throws Exception {
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"",
				"",
				35000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(GOLD, service.addNewCustomer(customer).getMemberType());
	}
	@Test
	public void addUserSILVER() throws Exception {
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"",
				"",
				15000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(SILVER, service.addNewCustomer(customer).getMemberType());
	}

	@Test
	public void addUserError() throws Exception {
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"",
				"",
				13000);
		when(repository.save(customer)).thenReturn(customer);
		assertThrows(Exception.class,()->service.addNewCustomer(customer),"salary < 15000");
	}



	//	Register process has to generate reference code from register date and
//	last 4 digits of phone number like this “YYYYMMDDXXXX” (ex.
//201708154652) and keep it in database.
	@Test
	public void addUserLast4Digits() throws Exception {
		String phone = "0639686884";
		Customer customer = new Customer( "famtest",
				"bangkok",
				phone,
				"",
				"",
				15000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(phone.substring(6), service.addNewCustomer(customer).getReferenceCode().substring(8));
	}

	@Test
	public void addUserGenerateDate() throws Exception {
		String dateGenerate= DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(LocalDateTime.now());
		Customer customer = new Customer( "famtest",
				"bangkok",
				"0639686884",
				"",
				"",
				15000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(dateGenerate, service.addNewCustomer(customer).getReferenceCode().substring(0,8));
	}

	@Test
	public void addUserGenerateReferenceCode() throws Exception {
		String dateGenerate= DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH).format(LocalDateTime.now());
		String phone = "0639686884";
		Customer customer = new Customer( "famtest",
				"bangkok",
				phone,
				"",
				"",
				15000);
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(dateGenerate+phone.substring(6), service.addNewCustomer(customer).getReferenceCode());
	}


}
