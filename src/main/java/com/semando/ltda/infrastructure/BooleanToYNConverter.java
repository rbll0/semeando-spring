package com.semando.ltda.infrastructure;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Essa classe transforma valores booleanos (true/false) em "S" ou "N" para o banco de dados.
 *
 * É útil quando o banco usa "S" para representar true e "N" para false.
 */
@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    /**
     * Converte o valor booleano para "S" ou "N" antes de salvar no banco.
     *
     * @param attribute O valor booleano (true ou false).
     * @return "S" se for true, "N" se for false.
     */
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "S" : "N";
    }

    /**
     * Converte o valor do banco ("S" ou "N") de volta para booleano.
     *
     * @param dbData O valor do banco ("S" ou "N").
     * @return true se for "S", false se for "N".
     */
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "S".equalsIgnoreCase(dbData);
    }
}
