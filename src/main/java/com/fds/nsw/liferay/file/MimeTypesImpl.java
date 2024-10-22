/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.liferay.file;

import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.ultility.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypesReaderMetKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
@Slf4j
public class MimeTypesImpl implements MimeTypes, MimeTypesReaderMetKeys {

	public MimeTypesImpl() {
		_detector = new DefaultDetector(
			org.apache.tika.mime.MimeTypes.getDefaultMimeTypes());

		URL url = org.apache.tika.mime.MimeTypes.class.getResource(
			"tika-mimetypes.xml");

		try {
			read(url.openStream());
		}
		catch (Exception e) {
			log.error("Unable to populate extensions map", e);
		}
	}

	public String getContentType(File file) {
		return getContentType(file, file.getName());
	}

	public String getContentType(File file, String fileName) {
		if ((file == null) || !file.exists()) {
			return getContentType(fileName);
		}

		InputStream is = null;

		try {
			is = TikaInputStream.get(file);

			return getContentType(is, fileName);
		}
		catch (FileNotFoundException fnfe) {
			return getContentType(fileName);
		}
		finally {
			StreamUtil.cleanUp(is);
		}
	}

	public String getContentType(InputStream inputStream, String fileName) {
		if (inputStream == null) {
			return getContentType(fileName);
		}

		String contentType = null;

		try {
			Metadata metadata = new Metadata();

			metadata.set("resourceName", fileName);

			MediaType mediaType = _detector.detect(
				TikaInputStream.get(inputStream), metadata);

			contentType = mediaType.toString();

			if (contentType.contains("tika")) {
				if (log.isDebugEnabled()) {
					log.debug("Retrieved invalid content type " + contentType);
				}

				contentType = getContentType(fileName);
			}

			if (contentType.contains("tika")) {
				if (log.isDebugEnabled()) {
					log.debug("Retrieved invalid content type " + contentType);
				}

				contentType = ContentTypes.APPLICATION_OCTET_STREAM;
			}
		}
		catch (Exception e) {
			log.error(e.getMessage());

			contentType = ContentTypes.APPLICATION_OCTET_STREAM;
		}

		return contentType;
	}

	public String getContentType(String fileName) {
		if (Validator.isNull(fileName)) {
			return ContentTypes.APPLICATION_OCTET_STREAM;
		}

		try {
			Metadata metadata = new Metadata();

			metadata.set("resourceName", fileName);

			MediaType mediaType = _detector.detect(null, metadata);

			String contentType = mediaType.toString();

			if (!contentType.contains("tika")) {
				return contentType;
			}
			else if (log.isDebugEnabled()) {
				log.debug("Retrieved invalid content type " + contentType);
			}
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}

		return ContentTypes.APPLICATION_OCTET_STREAM;
	}

	public Set<String> getExtensions(String contentType) {
		Set<String> extensions = _extensionsMap.get(contentType);

		if (extensions == null) {
			extensions = Collections.emptySet();
		}

		return extensions;
	}

	protected void read(InputStream stream) throws Exception {
		DocumentBuilderFactory documentBuilderFactory =
			DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder =
			documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(new InputSource(stream));

		Element element = document.getDocumentElement();

		if ((element == null) || !MIME_INFO_TAG.equals(element.getTagName())) {
			throw new Exception("Invalid configuration file");
		}

		NodeList nodeList = element.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			Element childElement = (Element)node;

			if (MIME_TYPE_TAG.equals(childElement.getTagName())) {
				readMimeType(childElement);
			}
		}
	}

	protected void readMimeType(Element element) {
		Set<String> mimeTypes = new HashSet<String>();

		Set<String> extensions = new HashSet<String>();

		String name = element.getAttribute(MIME_TYPE_TYPE_ATTR);

		mimeTypes.add(name);

		NodeList nodeList = element.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			Element childElement = (Element)node;

			if (ALIAS_TAG.equals(childElement.getTagName())) {
				String alias = childElement.getAttribute(ALIAS_TYPE_ATTR);

				mimeTypes.add(alias);
			}
			else if (GLOB_TAG.equals(childElement.getTagName())) {
				boolean isRegex = GetterUtil.getBoolean(
					childElement.getAttribute(ISREGEX_ATTR));

				if (isRegex) {
					continue;
				}

				String pattern = childElement.getAttribute(PATTERN_ATTR);

				if (!pattern.startsWith("*")) {
					continue;
				}

				String extension = pattern.substring(1);

				if (!extension.contains("*") && !extension.contains("?") &&
					!extension.contains("[")) {

					extensions.add(extension);
				}
			}
		}

		for (String mimeType : mimeTypes) {
			_extensionsMap.put(mimeType, extensions);
		}
	}


	private Detector _detector;
	private Map<String, Set<String>> _extensionsMap =
		new HashMap<String, Set<String>>();

}