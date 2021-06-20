package dev.gojava.core.helper;

import org.apache.commons.codec.digest.DigestUtils;

public final class UUIDHelper {

	public static String generateUUID_SHA256(String text) {
		return DigestUtils.sha256Hex(text);
	}
}
