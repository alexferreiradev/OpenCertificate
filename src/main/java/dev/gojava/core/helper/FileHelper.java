package dev.gojava.core.helper;

import java.io.File;
import java.io.FileInputStream;

public final class FileHelper {

	public static boolean isValidFile(String fileName) {
		if (fileName == null) {
			return false;
		}

		File file = new File(fileName);

		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			return fileInputStream.read() > 0;
		} catch (java.io.IOException e) {
			return false;
		}
	}

	public static String formatToValidFileName(String text) {
		String textFormatted = text.replaceAll("\\s", "_");
		textFormatted = textFormatted.replaceAll("/", "-");

		return textFormatted;
	}
}
