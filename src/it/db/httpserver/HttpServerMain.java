package it.db.httpserver;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import it.db.httpserver.executors.TestExecutor;
import it.db.httpserver.handlers.HtmlHandler;
import it.db.httpserver.handlers.ServletHandler;

public class HttpServerMain {
	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			server.createContext("/requests", new ServletHandler());
			server.createContext("/", new HtmlHandler());
			server.setExecutor(new TestExecutor()); // creates a default executor
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
