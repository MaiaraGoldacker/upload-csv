package com.upload.demo.util;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UploadUtils {

	public static boolean isNullOrBlank(String str) {
		return Objects.isNull(str) || str.isBlank();
	}
	
	public static DateTimeFormatter loadDateFormat() {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}
}
