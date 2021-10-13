package com.oauth.user.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

//@Converter(autoApply = true)
//public class UserGenderConverter implements AttributeConverter<Gender, Integer> {
//
//    @Override
//    public Integer convertToDatabaseColumn(Gender attribute) {
//        return attribute.getValue();
//    }
//
//    @Override
//    public Gender convertToEntityAttribute(Integer integer) {
//        return Stream.of(Gender.values())
//                .filter(gender -> gender.getValue() == integer)
//                .findFirst()
//                .orElseThrow(IllegalAccessError::new);
//    }
//}
