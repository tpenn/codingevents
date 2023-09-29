package org.launchcode.codingevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class CodingeventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingeventsApplication.class, args);
	}

	@EventListener({ApplicationReadyEvent.class})
	public static void applicationReadyEvent() {
		System.out.println("Application started, launching browser.");
		browse("http://localhost:8080");	// Replace with desired URL.
	}
	public static void browse(String url) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("open " + url);		// Mac
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
