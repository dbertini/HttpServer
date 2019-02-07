package it.db.httpserver.handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ServletHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String lsQuery = "";
		try {
			lsQuery = exchange.getRequestURI().getQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			byte body[] = exchange.getRequestBody().readAllBytes();
			String arrBody[] = new String(body).split("&");
			for (int i = 0; i < arrBody.length; i++) {
				String arrParam[] = arrBody[i].split("=");
				System.out.println("Chiave: " + arrParam[0]);
				System.out.println("Valore: " + arrParam[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String response = "hello world " + lsQuery;
		exchange.sendResponseHeaders(200, response.length());
		System.out.println(response);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
