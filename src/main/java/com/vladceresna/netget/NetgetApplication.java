package com.vladceresna.netget;

import com.github.valb3r.letsencrypthelper.tomcat.TomcatWellKnownLetsEncryptChallengeEndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TomcatWellKnownLetsEncryptChallengeEndpointConfig.class)
public class NetgetApplication {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				NetScanner.scan();
			}
		}).start();
		SpringApplication.run(NetgetApplication.class, args);
	}
}
