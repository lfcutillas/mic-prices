package com.inditex.prices.infrastructure.adapters;

import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.domain.exceptions.PriceNotFoundException;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.infrastructure.entities.PriceEntity;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapper;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapperImpl;
import com.inditex.prices.infrastructure.repositories.PriceInfraRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceAdapterTest {

    @InjectMocks
    private PriceAdapter underTest;

    @Mock
    private PriceInfraRepository repository;

    @Spy
    private PriceInfraMapper mapper = new PriceInfraMapperImpl();

    @Test
    void givenProductIdAndBrandIdAndApplicationDate_whenFindBetweenDates_thenThrowPriceNotFoundException() {

        // Given
        BrandId brandId = Instancio.create(BrandId.class);
        ProductId productId = Instancio.create(ProductId.class);
        ApplicationDate applicationDate = Instancio.create(ApplicationDate.class);

        when(repository.findBetweenDates(productId.value(), brandId.value(), applicationDate.value()))
                .thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> underTest.findBetweenDates(productId, brandId, applicationDate))
                .isInstanceOf(PriceNotFoundException.class);

    }

    @Test
    void givenProductIdAndBrandIdAndApplicationDate_whenFindBetweenDates_thenReturnPrices() {

        // Given
        BrandId brandId = Instancio.create(BrandId.class);
        ProductId productId = Instancio.create(ProductId.class);
        ApplicationDate applicationDate = Instancio.create(ApplicationDate.class);
        PriceEntity price = Instancio.create(PriceEntity.class);

        InOrder inOrder = inOrder(repository, mapper);
        when(repository.findBetweenDates(productId.value(), brandId.value(), applicationDate.value()))
                .thenReturn(Optional.of(price));

        // When
        Price priceResult = underTest.findBetweenDates(productId, brandId, applicationDate);

        // Then
        assertThat(priceResult).isNotNull();
        inOrder.verify(repository).findBetweenDates(productId.value(), brandId.value(), applicationDate.value());
        inOrder.verify(mapper).toDomain(price);

    }

}