package com.stackroute.expertservice.utils;

import com.stackroute.expertservice.constants.ExpertConstants;
import com.stackroute.expertservice.enums.SlotStatus;
import com.stackroute.expertservice.exception.InvalidArgumentException;
import com.stackroute.expertservice.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class ExpertUtils {

    private static String accessToken;
    private static String secretKey;
    private static boolean testingMode = false;

    private ExpertUtils() {
    }

    public static SlotStatus getSlotStatus(String slotStatusString) throws InvalidArgumentException {
        if (Objects.isNull(slotStatusString) || slotStatusString.isBlank()) {
            throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.CANT_BE_NULL_OR_BLANK);
        }
        for (SlotStatus slotStatus : SlotStatus.values()) {
            if (slotStatus.toString().equalsIgnoreCase(slotStatusString.trim())) {
                return slotStatus;
            }
        }
        throw new InvalidArgumentException(ExpertConstants.INVALID + ExpertConstants.PARAM_SLOT_STATUS + ExpertConstants.COLON + slotStatusString);
    }

    public static void validateToken(String email) {
        if (testingMode) {
            return;
        }
        if (Objects.isNull(accessToken)) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_MISSING_TOKEN);
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken.replace("Bearer ", "")).getBody();

            String emailId = (String) claims.get("sub");
            if (Objects.isNull(emailId) || !email.equalsIgnoreCase(emailId)) {
                throw new InvalidTokenException(ExpertConstants.MESSAGE_USER_UNAUTHORIZED);
            }
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_INVALID_TOKEN);
        }
    }

    public static void isExpert(){
        if (testingMode) {
            return;
        }
        if (Objects.isNull(accessToken)) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_MISSING_TOKEN);
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken.replace("Bearer ", "")).getBody();
            String role = (String) claims.get("role");
            if (Objects.isNull(role) || !"EXPERT".equalsIgnoreCase(role)) {
                throw new InvalidTokenException(ExpertConstants.MESSAGE_USER_UNAUTHORIZED);
            }
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new InvalidTokenException(ExpertConstants.MESSAGE_INVALID_TOKEN);
        }
    }

    public static void setAccessTokenAndSecretKey(HttpHeaders httpHeaders, String key) {
        accessToken = httpHeaders.getFirst(ExpertConstants.HEADER_AUTHORIZATION);
        secretKey = key;
    }

    public static void enableTestingMode() {
        testingMode = true;
    }

    public static void disableTestingMode() {
        testingMode = false;
    }
}
