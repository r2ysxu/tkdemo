package com.tk.demo.graphql.query.model;

import org.apache.tomcat.util.codec.binary.Base64;

public interface Node {

	public static String generateId(Long id) {
		return Base64.encodeBase64URLSafeString(Long.toString(id).getBytes());
	}

	public static Long decodeId(String id) {
		return Long.parseLong(new String(Base64.decodeBase64URLSafe(id)));
	}

	public String getId();
}
