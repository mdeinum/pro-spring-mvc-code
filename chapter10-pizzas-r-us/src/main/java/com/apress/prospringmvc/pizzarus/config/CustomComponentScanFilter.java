package com.apress.prospringmvc.pizzarus.config;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class CustomComponentScanFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		String className = metadataReader.getClassMetadata().getClassName();

		if (className.startsWith("com.apress.prospringmvc.pizzarus.config")
				|| className.startsWith("com.apress.prospringmvc.pizzarus.web.config")) {
			return true;
		}
		return false;
	}
}
