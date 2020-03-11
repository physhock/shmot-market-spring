package ru.spbpu.services;

import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.spbpu.models.actors.Administrator;
import ru.spbpu.models.actors.Buyer;
import ru.spbpu.models.actors.Seller;
import ru.spbpu.models.actors.User;
import ru.spbpu.repositories.actors.AdministratorRepository;
import ru.spbpu.repositories.actors.BuyerRepository;
import ru.spbpu.repositories.actors.SellerRepository;

import java.util.Map;
import java.util.Optional;

/**
 * @author fshkolni
 */
@Service
public class MarketplaceService {

    private final AdministratorRepository administratorRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public MarketplaceService(AdministratorRepository administratorRepository,
                              BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.administratorRepository = administratorRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    public Optional<? extends User> login(@NonNull Map<String, Object> data) {
        final String username = data.get("username").toString();
        final String password = data.get("password").toString();

        final Optional<Administrator> administrator =
                administratorRepository.findOne(Example.of(new Administrator(username, password)));
        final Optional<Buyer> buyer = buyerRepository.findOne(Example.of(new Buyer(username, password)));
        final Optional<Seller> seller = sellerRepository.findOne(Example.of(new Seller(username, password)));

        return administrator.isPresent() ? administrator
                : seller.isPresent() ? seller
                : buyer.isPresent() ? buyer
                : Optional.empty();
    }


}
