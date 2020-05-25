package ru.spbpu.services;

import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.spbpu.models.actors.Administrator;
import ru.spbpu.models.actors.Buyer;
import ru.spbpu.models.actors.Seller;
import ru.spbpu.models.actors.User;
import ru.spbpu.models.system.*;
import ru.spbpu.repositories.actors.AdministratorRepository;
import ru.spbpu.repositories.actors.BuyerRepository;
import ru.spbpu.repositories.actors.SellerRepository;
import ru.spbpu.repositories.system.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author fshkolni
 */
@Service
public class MarketplaceService {

    private final AdministratorRepository administratorRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    private final ItemRepository itemRepository;
    private final BetRepository betRepository;
    private final AskRepository askRepository;
    private final DealRepository dealRepository;
    private final OrderRepository orderRepository;

    public MarketplaceService(AdministratorRepository administratorRepository,
                              BuyerRepository buyerRepository, SellerRepository sellerRepository,
                              ItemRepository itemRepository, BetRepository betRepository,
                              AskRepository askRepository, DealRepository dealRepository,
                              OrderRepository orderRepository) {
        this.administratorRepository = administratorRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.itemRepository = itemRepository;
        this.betRepository = betRepository;
        this.askRepository = askRepository;
        this.dealRepository = dealRepository;
        this.orderRepository = orderRepository;
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

    public List<Item> getAllItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Bet> getHighestBets() {
        final Map<Long, Integer> highestBets = StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(bet -> bet.getItem().getId(), Bet::getBet, (b1, b2) -> b1 > b2 ? b1 : b2));
        return StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .filter(bet -> highestBets.get(bet.getItem().getId()).equals(bet.getBet()))
                .collect(Collectors.toList());
    }

    public List<Ask> getLowestAsk() {
        final Map<Long, Integer> lowestAsk = StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(ask -> ask.getItem().getId(), Ask::getAsk, (b1, b2) -> b1 < b2 ? b1 : b2));
        return StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .filter(ask -> lowestAsk.get(ask.getItem().getId()).equals(ask.getAsk()))
                .collect(Collectors.toList());
    }

    public void checkIfDeal(Bet bet) {
        StreamSupport.stream(askRepository.findAll().spliterator(), false)
                .filter(ask -> ask.getItem().getId().equals(bet.getItem().getId()) && ask.getAsk() == bet.getBet())
                .findFirst()
                .ifPresent(ask -> createDeal(bet, ask));
    }

    public void checkIfDeal(Ask ask) {
        StreamSupport.stream(betRepository.findAll().spliterator(), false)
                .filter(bet -> bet.getItem().getId().equals(ask.getItem().getId()) && bet.getBet() == ask.getAsk())
                .findFirst()
                .ifPresent(bet -> createDeal(bet, ask));
    }

    private Deal createDeal(Bet bet, Ask ask) {
        final Administrator administrator = administratorRepository.findAll().stream().findAny().orElseThrow();
        final Deal deal = dealRepository.save(new Deal(ask, bet, administrator));
        administrator.getDeals().add(deal);
        administratorRepository.save(administrator);
        return deal;
    }

    public void newOrder(Long dealId){
        final Deal deal = dealRepository.findById(dealId).get();
        final Buyer buyer = deal.getBet().getBuyer();
        final Order order = new Order(deal.getBet().getItem(), "123321", buyer);
        orderRepository.save(order);
        buyer.getOrders().add(order);
        buyerRepository.save(buyer);
    }

}
