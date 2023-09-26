package com.ks.todo.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * String to Collection Converter
 * 
 * @author Ken Shen
 * @version 1.0
 */
@Converter(autoApply = true)
public class ListCollectionConverter implements AttributeConverter<List<String>, String> {
	
	private static final String DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if (CollectionUtils.isEmpty(attribute)) {
			return null;
		}

		return String.join(DELIMITER, attribute);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (StringUtils.isBlank(dbData)) {
			return Collections.emptyList();
		}
		
		List<String> resultCollection = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(dbData, DELIMITER);

		while (st.hasMoreTokens()) {
			resultCollection.add(st.nextToken());
		}

		return resultCollection;
	}

}
