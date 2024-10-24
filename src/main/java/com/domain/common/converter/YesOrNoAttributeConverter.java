package com.domain.common.converter;


import com.domain.common.enums.YesOrNo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * DB 값 <-> 상수 상호 변환
 */
@Converter(autoApply = true)
public class YesOrNoAttributeConverter implements AttributeConverter<YesOrNo, String> {
    @Override
    public String convertToDatabaseColumn(YesOrNo attribute) {
        return null == attribute ? null : attribute.getCode();
    }

    @Override
    public YesOrNo convertToEntityAttribute(String dbData) {
        return YesOrNo.codeOf(dbData).orElse(null);
    }
}
