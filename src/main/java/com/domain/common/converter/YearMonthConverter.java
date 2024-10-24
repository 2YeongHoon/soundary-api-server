package com.domain.common.converter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.core.convert.converter.Converter;

/**
 * 파라메터 문자열을 객체로 변환
 */
public class YearMonthConverter implements Converter<String, YearMonth> {

    @Override
    public YearMonth convert(String value) {
        return Optional.ofNullable(value).isPresent()
            ? YearMonth.parse(value, DateTimeFormatter.ofPattern("yyyyMM"))
            : null;
    }
}
