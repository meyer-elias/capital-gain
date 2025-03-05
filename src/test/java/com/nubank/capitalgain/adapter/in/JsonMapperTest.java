package com.nubank.capitalgain.adapter.in;

import com.nubank.capitalgain.application.domain.commons.TypeOperation;
import com.nubank.capitalgain.application.domain.dto.FeeDto;
import com.nubank.capitalgain.application.domain.dto.TradeDto;
import java.util.Arrays;
import java.util.List;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
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

        List<TradeDto> tradesArray = Arrays.asList((TradeDto[]) jsonMapper.readValue(json, TradeDto[].class));

        assertThat(tradesArray)
            .hasSize(3)
            .filteredOn(t -> t.typeOperation() == TypeOperation.BUY)
            .hasSize(1);

        assertThat(tradesArray)
            .filteredOn(t -> t.typeOperation() == TypeOperation.SELL)
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