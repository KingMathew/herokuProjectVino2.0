package Utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwTokenHelper {

    private static JwTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 30;
    final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwTokenHelper() {

    }

    public static JwTokenHelper getInstance() {
        if (jwTokenHelper == null) {
            jwTokenHelper = new JwTokenHelper();
        }
        return jwTokenHelper;
    }

    public String generatePrivateKey(String username, String password) {
        return Jwts
                .builder()
                .setSubject(username)
                .setSubject(password)
                .setExpiration(getExpirationDate())
                .signWith(key)
                .compact();
    }

    public boolean claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(privateKey);
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                return false;
            } else if (e instanceof MalformedJwtException) {
                return false;
            } else if (e instanceof SignatureException) {
                return false;
            }
            return true;
        }
        return true;
    }

    @NotNull
    private Date getExpirationDate() {
        long currentTimeMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeMillis + expMilliSeconds);
    }
}
