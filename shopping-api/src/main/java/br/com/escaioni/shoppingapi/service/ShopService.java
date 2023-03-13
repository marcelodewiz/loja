package br.com.escaioni.shoppingapi.service;

import br.com.escaioni.shoppingapi.converter.DTOConverter;
import br.com.escaioni.shoppingapi.model.Shop;
import br.com.escaioni.shoppingapi.repository.ReportRepositoryImpl;
import br.com.escaioni.shoppingapi.repository.ShopRepository;
import br.com.escaioni.shoppingclient.dto.ShopDTO;
import br.com.escaioni.shoppingclient.dto.ShopReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ReportRepositoryImpl reportRepository;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository
                .findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long id){
        Optional<Shop> shop = shopRepository.findById(id);
        if(shop.isPresent()){
            return DTOConverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO){
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x ->x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        List<Shop> shops = reportRepository.getShopByFilters( dataInicio, dataFim, valorMinimo);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        return reportRepository.getReportByDate(dataInicio, dataFim);
    }
}
