package com.inditex.prices.application.usecases;

import com.inditex.prices.application.mappers.PriceMapper;
import com.inditex.prices.application.mappers.PriceMapperImpl;
import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.entities.Price;
import com.inditex.prices.domain.ports.PricePort;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceUseCaseImplTest {

    @InjectMocks
    private FindPriceUseCaseImpl underTest;

    @Mock
    private PricePort port;

    @Spy
    private PriceMapper mapper = new PriceMapperImpl();

    @Test
    void givenProductIdAndBrandIdAndApplicationDate_whenFindBetweenDates_thenReturnFinalDate() {

        // Given
        BrandId brandId = Instancio.create(BrandId.class);
        ProductId productId = Instancio.create(ProductId.class);
        ApplicationDate applicationDate = Instancio.create(ApplicationDate.class);
        Price price = Instancio.create(Price.class);

        InOrder inOrder = inOrder(port, mapper);
        when(port.findBetweenDates(productId, brandId, applicationDate)).thenReturn(price);

        // When
        FinalPrice result = underTest.findBetweenDates(productId, brandId, applicationDate);

        // Then
        assertThat(result).isNotNull();
        inOrder.verify(port).findBetweenDates(productId, brandId, applicationDate);
        inOrder.verify(mapper).toResponse(price);

    }

}