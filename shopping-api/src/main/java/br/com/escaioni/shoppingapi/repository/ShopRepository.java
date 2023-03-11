package br.com.escaioni.shoppingapi.repository;

import br.com.escaioni.shoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> , ReportRepository{

    public List<Shop> findAllByUserIdentifier(
            String userIdentifier);

    public List<Shop> findAllByTotalGreaterThan(Float total);

    public List<Shop> findAllByDateGreaterThan(LocalDateTime date);

}
