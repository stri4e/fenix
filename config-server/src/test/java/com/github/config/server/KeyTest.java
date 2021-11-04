package com.github.config.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.config.server.models.KeysStore;
import com.github.config.server.utils.TokenGenerator;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class KeyTest {

    @Test
    public void t() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, KeysStore> keys = new HashMap<>();
        TokenGenerator tokenGenerator = new TokenGenerator();
        KeysInfo k1 = new KeysInfo(
                "ROLE_MANAGER",
                10,
                321312312,
                321321312,
                "ACCESS_TOKEN",
                "ACCESS_COOKIE",
                "REFRESH_COOLIE"
        );
        KeysInfo k2 = new KeysInfo(
                "ROLE_ADMIN",
                10,
                321312312,
                321321312,
                "ACCESS_TOKEN",
                "ACCESS_COOKIE",
                "REFRESH_COOLIE"
        );
        KeysInfo k3 = new KeysInfo(
                "ROLE_USER",
                10,
                321312312,
                321321312,
                "ACCESS_TOKEN",
                "ACCESS_COOKIE",
                "REFRESH_COOLIE"
        );
        KeysStore key1 = tokenGenerator.toKeysStore(k1, SignatureAlgorithm.HS512);
        KeysStore key2 = tokenGenerator.toKeysStore(k2, SignatureAlgorithm.HS512);
        KeysStore key3 = tokenGenerator.toKeysStore(k3, SignatureAlgorithm.HS512);

        keys.put("ROLE_MANAGER", key1);
        keys.put("ROLE_ADMIN", key2);
        keys.put("ROLE_USER", key3);

        String str = mapper.writeValueAsString(keys);
        System.out.println(mapper.writeValueAsString(str));
    }

    public static class Key {

        private String id;

        private String key;

        private Integer priority;

        public Key() {
        }

        public Key(String id, String key, Integer priority) {
            this.id = id;
            this.key = key;
            this.priority = priority;
        }

        public String getId() {
            return id;
        }

        public String getKey() {
            return key;
        }

        public Integer getPriority() {
            return priority;
        }
    }

}
