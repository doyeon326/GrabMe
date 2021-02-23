package com.grabme.service;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class MessageService {

	@Value("${coolsms.devHee.apikey}")
	private String api_key;

	@Value("${coolsms.devHee.apisecret}")
	private String api_secret;

	@Value("${coolsms.devHee.fromnumber}")
	private String from_number;

	public void sendMessage(String toNumber) {

		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", toNumber);
		params.put("from", from_number);
		params.put("type", "SMS");
		params.put("text", "Coolsms Testing Message!");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

}
