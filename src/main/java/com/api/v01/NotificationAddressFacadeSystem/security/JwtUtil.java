//package com.api.v01.NotificationAddressFacadeSystem.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//import java.util.Date;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.SignatureException;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//public class JwtUtil {
//
//    private final String secret = "your-very-secure-secret-key"; // Must be 256 bits for HS256
//    private final byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
//
//    /**
//     * Extract claims manually from a JWT token.
//     *
//     * @param token the JWT token
//     * @return the Claims object
//     * @throws SignatureException if the signature is invalid
//     */
//    public Claims getClaimsFromToken(String token) {
//        try {
//            // Split the token into parts: Header, Payload, Signature
//            String[] parts = token.split("\\.");
//            if (parts.length != 3) {
//                throw new IllegalArgumentException("Invalid JWT token format");
//            }
//
//            String header = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
//            String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
//            String signature = parts[2];
//
//            // Verify the signature
//            String expectedSignature = computeSignature(parts[0] + "." + parts[1]);
//            if (!expectedSignature.equals(signature)) {
//                throw new SignatureException("Invalid JWT signature");
//            }
//
//            // Parse the claims from the payload
//            return Jwts.parserBuilder()
//                    .deserializeJsonWith((bytes) -> new String(bytes, StandardCharsets.UTF_8)) // Use default deserializer
//                    .build()
//                    .parseClaimsJwt(payload) // Claims only
//                    .getBody();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to extract claims from token", e);
//        }
//    }
//
//    /**
//     * Compute the HMAC SHA-256 signature for a message.
//     *
//     * @param message the message to sign
//     * @return the Base64-encoded signature
//     */
//    private String computeSignature(String message) {
//        try {
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(new SecretKeySpec(secretBytes, "HmacSHA256"));
//            byte[] signatureBytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
//            return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to compute signature", e);
//        }
//    }
//}
//
