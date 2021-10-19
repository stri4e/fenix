package com.github.config.server.utils;

import com.github.config.server.dto.KeysInfo;
import com.github.config.server.models.AccessKey;
import com.github.config.server.models.KeysStore;
import com.github.config.server.models.RefreshKey;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.UUID;

public class TokenGenerator {

    public String generateKey(SignatureAlgorithm signatureAlgorithm) {
        Key key = Keys.secretKeyFor(signatureAlgorithm);
        return Encoders.BASE64.encode(key.getEncoded());
    }

    public Key restoreFromStr(String key) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public KeysStore toKeysStore(KeysInfo keysInfo, SignatureAlgorithm signatureAlgorithm) {
        return new KeysStore(
                keysInfo.getPriority(),
                new AccessKey(
                        UUID.randomUUID().toString(),
                        keysInfo.getAccessTokenExpireTime(),
                        generateKey(signatureAlgorithm),
                        keysInfo.getAccessTokenHeaderName(),
                        keysInfo.getAccessTokenCookieName()
                ),
                new RefreshKey(
                        UUID.randomUUID().toString(),
                        keysInfo.getRefreshTokenExpireTime(),
                        generateKey(signatureAlgorithm),
                        keysInfo.getRefreshTokenCookieName()
                )
        );
    }

}
