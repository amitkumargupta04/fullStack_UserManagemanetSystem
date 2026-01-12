package amit.com.User_Management.security;

import amit.com.User_Management.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration; // milliseconds

    // ------------------ Signing key ------------------
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // ------------------ Token generation ------------------
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())          // Email as subject
                .claim("role", user.getRole())       // role claim
                .setIssuedAt(new Date())             // issued now
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) // expiry
                .signWith(getSigningKey())           // sign with secret
                .compact();
    }

    // ------------------ 1️⃣ Extract Email from token ------------------
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Generic method to extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // verify signature
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    // ------------------ 2️⃣ Check if token is valid for a user ------------------
    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);             // token se email nikala
        return (email.equals(user.getEmail()) && !isTokenExpired(token));  // email match + expiry check
    }

    // Check if token expired
    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
