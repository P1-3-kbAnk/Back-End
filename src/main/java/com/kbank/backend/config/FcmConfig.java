package com.kbank.backend.config;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@PropertySource("classpath:application-fcm.properties")
public class FcmConfig {

    @Value("${scope}")
    private String scope;
    @Value("${fcm.secret-file}")
    private String secretFileName;

    @PostConstruct
    public void initialize() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(secretFileName).getInputStream())
                    .createScoped(Arrays.asList(scope));

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(googleCredentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase application has been initialized");
            }

            // 토큰 확인
            String token = getAccessToken(googleCredentials);
            System.out.println("Access Token: " + token);

        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
        }
    }

    // 액세스 토큰을 가져오는 메서드
    private String getAccessToken(GoogleCredentials googleCredentials) throws IOException {
        AccessToken token = googleCredentials.refreshAccessToken();
        return token.getTokenValue();
    }
}
