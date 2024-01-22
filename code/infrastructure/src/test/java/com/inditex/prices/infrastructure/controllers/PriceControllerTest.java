package com.inditex.prices.infrastructure.controllers;

import com.inditex.prices.domain.dtos.FinalPrice;
import com.inditex.prices.domain.records.ApplicationDate;
import com.inditex.prices.domain.records.BrandId;
import com.inditex.prices.domain.records.ProductId;
import com.inditex.prices.domain.usecases.FindPriceUseCase;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapper;
import com.inditex.prices.infrastructure.mappers.PriceInfraMapperImpl;
import lombok.SneakyThrows;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private static final String PRICES_URL = "/prices";

    @Mock
    private FindPriceUseCase findPriceUseCase;

    @Spy
    private PriceInfraMapper mapper = new PriceInfraMapperImpl();

    @InjectMocks
    private PriceController priceController;

    @SneakyThrows
    @Test
    void givenProductIdAndBrandIdAndApplicationDate_whenFindPrice_thenReturnsOkResponse() {

        // Given
        int productId = 123;
        int brandId = 456;
        OffsetDateTime applicationDate = OffsetDateTime.now();
        ProductId mockProductId = new ProductId(productId);
        BrandId mockBrandId = new BrandId(brandId);
        ApplicationDate mockApplicationDate = new ApplicationDate(applicationDate.toLocalDateTime());

        FinalPrice mockFinalPrice = Instancio.create(FinalPrice.class);

        when(findPriceUseCase.findBetweenDates(mockProductId, mockBrandId, mockApplicationDate)).thenReturn(mockFinalPrice);

        // When & Then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
        mockMvc.perform(MockMvcRequestBuilders.get(PRICES_URL)
                .param("productId", String.valueOf(productId))
                .param("brandId", String.valueOf(brandId))
                .param("applicationDate", applicationDate.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
