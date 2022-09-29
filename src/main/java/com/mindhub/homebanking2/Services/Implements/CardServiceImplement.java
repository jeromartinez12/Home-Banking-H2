package com.mindhub.homebanking2.Services.Implements;

import com.mindhub.homebanking2.Models.Card;
import com.mindhub.homebanking2.Repositories.CardRepository;
import com.mindhub.homebanking2.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {

	@Autowired
	CardRepository cardRepository;

	@Override
	public void saveCard(Card card) {cardRepository.save(card);}

	@Override
	public Boolean stopCard(Card card) {card.setActive(false);
		cardRepository.save(card);
		return null;
	}
	@Override
	public Card getCardById(Long id) {
		return cardRepository.findById(id).get();
	}

}
