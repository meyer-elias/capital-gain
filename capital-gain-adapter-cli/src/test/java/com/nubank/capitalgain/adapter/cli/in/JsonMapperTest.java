package com.nubank.capitalgain.adapter.cli.in;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.nubank.capitalgain.adapter.cli.in.request.TradeRequest;
import com.nubank.capitalgain.core.calculator.application.dto.FeeDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Case Json Serializer | Deserializar")
class JsonMapperTest {

    private final JsonMapper jsonMapper = new JsonMapper();

    @Test
    @DisplayName("Unserializable Test transform object ")
    void shouldTestSuccessfullyReadString() {
        final String json = """
            [{"operation":"buy","quantity":10000,"unit-cost":10.00},
            {"operation":"sell","quantity":5000,"unit-cost":20.00},
            {"operation":"sell","quantity":5000,"unit-cost":5.00}]}
            """;

        List<TradeRequest> tradesArray = Arrays.asList((TradeRequest[]) jsonMapper.readValue(json, TradeRequest[].class));

        assertThat(tradesArray)
            .hasSize(3)
            .filteredOn(t -> t.typeOperation().equalsIgnoreCase("BUY"))
            .hasSize(1);

        assertThat(tradesArray)
            .filteredOn(t -> t.typeOperation().equalsIgnoreCase("SELL"))
            .hasSize(2);
    }

    @Test
    void shoultTestSuccessfullyWriteString() {
        final List<FeeDto> fees = Arrays.asList(
            new FeeDto("0.00"),
            new FeeDto("10000.00"),
            new FeeDto("0.00"));

        final String json = jsonMapper.writeValuesAsString(fees);

        assertThatJson(json)
            .isArray()
            .hasSize(3)
            .isEqualTo("[{tax:0.00},{tax:10000.00},{tax:0.00}]");
    }
}
