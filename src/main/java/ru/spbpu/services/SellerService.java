package ru.spbpu.services;

import org.springframework.stereotype.Service;
import ru.spbpu.models.actors.Seller;
import ru.spbpu.models.system.Ask;
import ru.spbpu.repositories.actors.SellerRepository;
import ru.spbpu.repositories.system.AskRepository;

@Service
public class SellerService {

    private final AskRepository askRepository;
    private final SellerRepository sellerRepository;
    private final MarketplaceService marketplaceService;

    public SellerService(AskRepository askRepository, SellerRepository sellerRepository,
                         MarketplaceService marketplaceService) {
        this.askRepository = askRepository;
        this.sellerRepository = sellerRepository;
        this.marketplaceService = marketplaceService;
    }

    public Long placeAsk(final Ask ask, final Long sellerId) {
        final Seller seller = sellerRepository.findById(sellerId).get();
        ask.setSeller(seller);
        final Ask savedAsk = askRepository.save(ask);
        seller.getAsks().add(savedAsk);
        sellerRepository.save(seller);
        marketplaceService.checkIfDeal(ask);
        return savedAsk.getId();
    }
}
