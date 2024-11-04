package io.github.damianlebiedz.financemanager;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor

public class Data {
    private final int id;
    private final String name;
    private final String category;
    private final BigDecimal price;
    private final Date date;
}