package it.db.httpserver.handlers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HtmlHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			
			String lsUrlQuery = exchange.getRequestURI().getPath();
			System.out.println(lsUrlQuery);
			

			
			
			String response = "";
			for (Iterator<String> iterator = Files
					.readAllLines(Paths.get("/Users/davidbertini/work/eclipse-workspace/HttpServer"+lsUrlQuery))
					.iterator(); iterator.hasNext();) {
				response += iterator.next();
			}
			
			exchange.sendResponseHeaders(200, 0);
	        try (BufferedOutputStream out = new BufferedOutputStream(exchange.getResponseBody())) {
	            try (ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes())) {
	                byte [] buffer = new byte [1024];
	                int count ;
	                while ((count = bis.read(buffer)) != -1) {
	                    out.write(buffer, 0, count);
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			sendErrorMessage(exchange);
		}
	}

	private void sendErrorMessage(HttpExchange exchange) throws IOException {
		String pnf = "Page not found";
		exchange.sendResponseHeaders(404, pnf.length());
		OutputStream os = exchange.getResponseBody();
		os.write(pnf.getBytes());
		os.close();
	}

}
