package ru.spbpu.services;

import org.springframework.stereotype.Service;
import ru.spbpu.models.actors.Administrator;
import ru.spbpu.repositories.actors.AdministratorRepository;

import java.util.Optional;

@Service
public class AdminService {
    private final AdministratorRepository administratorRepository;
    private final MarketplaceService marketplaceService;

    public AdminService(AdministratorRepository administratorRepository, MarketplaceService marketplaceService) {
        this.administratorRepository = administratorRepository;
        this.marketplaceService = marketplaceService;
    }

    public Optional<Administrator> getDeals(Long id) {
        return administratorRepository.findById(id);
    }

    public void approve(Long dealId) {
        marketplaceService.newOrder(dealId);
    }
}
