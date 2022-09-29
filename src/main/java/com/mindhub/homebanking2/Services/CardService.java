package com.mindhub.homebanking2.Services;

import com.mindhub.homebanking2.Models.Card;

public interface CardService {

	void saveCard (Card card);
	Boolean stopCard(Card card);
	Card getCardById(Long id);
}
