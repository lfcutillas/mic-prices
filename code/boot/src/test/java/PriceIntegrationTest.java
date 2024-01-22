import com.inditex.prices.Application;
import com.inditex.prices.model.PriceResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("dateProvider")
    void should_return_ok_when_dates_are_provided(String applicationDate) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/prices")
                .queryParam("productId", "35455")
                .queryParam("brandId", "1")
                .queryParam("applicationDate", applicationDate);

        ResponseEntity<PriceResponse> result = restTemplate.getForEntity(builder.toUriString(), PriceResponse.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    private static Stream<Arguments> dateProvider() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00Z"),
                Arguments.of("2020-06-14T16:00:00Z"),
                Arguments.of("2020-06-14T21:00:00Z"),
                Arguments.of("2020-06-15T10:00:00Z"),
                Arguments.of("2020-06-16T21:00:00Z")
        );
    }

}