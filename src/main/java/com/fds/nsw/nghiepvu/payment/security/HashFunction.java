package com.fds.nsw.nghiepvu.payment.security;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashFunction {

	public String hashAllFields(Map fields, String SECURE_SECRET) {
		// create a list and sort it
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);

		// iterate through the list and add the remaining field values
		Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {

				buf.append(fieldValue);
			}
		}

		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] ba = md.digest(buf.toString().getBytes("UTF-8"));

			// convert the byte to hex format

			for (int i = 0; i < ba.length; i++) {
				sb.append(Integer.toString((ba[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return sb.toString();
	}
}
