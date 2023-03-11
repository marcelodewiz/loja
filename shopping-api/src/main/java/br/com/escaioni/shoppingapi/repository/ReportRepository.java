package br.com.escaioni.shoppingapi.repository;

import br.com.escaioni.shoppingapi.dto.ShopReportDTO;
import br.com.escaioni.shoppingapi.model.Shop;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);
}
