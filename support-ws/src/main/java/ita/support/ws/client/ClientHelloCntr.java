package ita.support.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("clients")
public class ClientHelloCntr {

	private static Logger logger = LoggerFactory.getLogger(ClientHelloCntr.class);
	private static final Marker MARKER = MarkerFactory.getMarker("TEST_MARKER");

	@RequestMapping("hello")
	public String syHello() {
		logger.info("Method syHello");
		logger.debug("TEST DEBUG");
		logger.warn("TEST WARN");
		logger.error("TEST ERROR");
		logger.info(
				"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		logger.error("NULL", new NullPointerException());

		return "HelloWorld!!!";
	}

	@RequestMapping("set")
	public String sySet() {

		String str = "NEW TEST STRING";

		logger.info(MARKER, "TEST INFO WITH MARKER str={}", str);
		return "HelloWorld!!!!!!!";
	}

	/*
	 * Method return JSONArray All Client
	 *
	 */
	@RequestMapping("getAllClients")
	public String getAllClients() {

		/*
		 * read data from file dbuser.json
		 */
		StringBuilder resultJson = new StringBuilder();
		InputStream resource = ClientHelloCntr.class.getResourceAsStream("/dbusers.json");
		BufferedReader dbuser = new BufferedReader(new InputStreamReader(resource));

		try {
			String line = dbuser.readLine();

			while (line != null) {
				System.out.println(line);
				resultJson.append(line);
				resultJson.append(System.lineSeparator());
				line = dbuser.readLine();
			}
		} catch (IOException e) {
			// logging
		} finally {
			try {
				dbuser.close();
			} catch (IOException e) {
				// loging
			}
		}

		/*
		 * parse data objects
		 */
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(resultJson.toString());

		if (element.isJsonObject()) {
			JsonArray users = (JsonArray) element.getAsJsonObject().get("users");
			return users.toString();
		} else {
			// logging
			return "";
		}
	}

	/*
	 * Method return JSONObject Client at id
	 *
	 */
	@RequestMapping(value = "/getClient/{id}")
	public String getClient(@PathVariable("id") int id) {

		/*
		 * read data from file dbuser.json
		 */
		StringBuilder resultJson = new StringBuilder();
		InputStream resource = ClientHelloCntr.class.getResourceAsStream("/dbusers.json");
		BufferedReader dbuser = new BufferedReader(new InputStreamReader(resource));

		try {
			String line = dbuser.readLine();

			while (line != null) {
				resultJson.append(line);
				resultJson.append(System.lineSeparator());
				line = dbuser.readLine();
			}
		} catch (IOException e) {
			// logging
		} finally {
			try {
				dbuser.close();
			} catch (IOException e) {
				// logging
			}
		}

		/*
		 * parse data objects
		 */
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(resultJson.toString());

		if (element.isJsonObject()) {
			JsonArray users = (JsonArray) element.getAsJsonObject().get("users");
			JsonObject user = (JsonObject) users.get(id - 1);
			return user.toString();
		} else {
			// logging
			return "";
		}

	}

	@RequestMapping("putClient")
	public String putClient() {

		return "ok";
	}

	@RequestMapping("deleteClient")
	public String deleteClient() {

		return "ok";
	}

}
