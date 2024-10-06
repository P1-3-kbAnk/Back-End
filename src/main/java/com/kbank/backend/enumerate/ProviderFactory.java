package com.kbank.backend.enumerate;

public class ProviderFactory {
    public static Provider of(String provider) {
        return switch (provider) {
            case "KAKAO" -> Provider.KAKAO;
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };
    }
}
