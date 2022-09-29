package com.mindhub.homebanking2;
import com.mindhub.homebanking2.Utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class CardUtilTest {

	@Test
	public void cardNumberIsCreated(){
		String cardNumber = CardUtils.getCardNumber();
		assertThat(cardNumber,is(not(emptyOrNullString())));

	}
}
