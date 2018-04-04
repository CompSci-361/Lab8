import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    static Directory employeeDirectory = new Directory();
	public static void main(String[] args) throws Throwable {
        // set up a simple HTTP server on our local host
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // create a context to get the request to display the results
        server.createContext("/displayresults", new DisplayHandler());
        
        server.createContext("/style.css", new StyleHandler());

        // create a context to get the request for the POST
        server.createContext("/sendresults", new PostHandler());
        server.setExecutor(null); // creates a default executor

        // get it going
        System.out.println("Starting Server...");
        server.start();
	}
	
	static class StyleHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            // write out the response
        	byte[] css = Files.readAllBytes(Paths.get("src/style.css"));
        	
            t.sendResponseHeaders(200, css.length);
            OutputStream os = t.getResponseBody();
            os.write(css);
            os.close();
        }
	}

    static class DisplayHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            // write out the response
        	String html = employeeDirectory.getDirectoryAsHtml();
            t.sendResponseHeaders(200, html.length());
            OutputStream os = t.getResponseBody();
            os.write(html.getBytes());
            os.close();
        }
    }

    static class PostHandler implements HttpHandler {
        public void handle(HttpExchange transmission) throws IOException {
            // set up a stream to read the body of the request
            InputStream inputStr = transmission.getRequestBody();

            // string to hold the result of reading in the request
            StringBuilder sb = new StringBuilder();

            // read the characters from the request byte by byte and build up the sharedResponse
            int nextChar = inputStr.read();
            while (nextChar > -1) {
                sb=sb.append((char)nextChar);
                nextChar=inputStr.read();
            }

            String jsonOfEmployee = sb.toString();
            
            employeeDirectory.addEmployeeFromJson(jsonOfEmployee);
            employeeDirectory.updateDirectory();

            // assume that stuff works all the time
            transmission.sendResponseHeaders(201, 0);
        }
    }
}
