package com.semando.ltda.infrastructure;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Essa classe transforma valores booleanos (true/false) em "Y" ou "N" para o banco de dados.
 *
 * É útil quando o banco usa "Y" para representar true e "N" para false.
 */
@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    /**
     * Converte o valor booleano para "Y" ou "N" antes de salvar no banco.
     *
     * @param attribute O valor booleano (true ou false).
     * @return "Y" se for true, "N" se for false.
     */
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    /**
     * Converte o valor do banco ("Y" ou "N") de volta para booleano.
     *
     * @param dbData O valor do banco ("Y" ou "N").
     * @return true se for "Y", false se for "N".
     */
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equalsIgnoreCase(dbData);
    }
}
