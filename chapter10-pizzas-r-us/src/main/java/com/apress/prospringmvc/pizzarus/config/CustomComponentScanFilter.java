package com.apress.prospringmvc.pizzarus.config;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class CustomComponentScanFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		Package classPackage = metadataReader.getClassMetadata().getClass().getPackage();

		if (classPackage.getName().startsWith("com.apress.prospringmvc.pizzarus.config")
				|| classPackage.toString().startsWith("com.apress.prospringmvc.pizzarus.web.config")) {
			return false;
		}
		return true;
	}
}
