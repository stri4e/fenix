package com.github.users.center;

import com.github.users.center.utils.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

@Configuration
public class TestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();

        properties.setProperty("keys.store", "{\"dd55d052-0cc8-4d6d-9057-f3c0c8e47b4e\":\"PJA/5w8+M+/M1HCUFVN7H5JXfqT2qtyyK9ZpKdTzfoqklVhigahDQmNWEDY04hF5mFQVtjEC8BzCjM9iWpf9GQ==\",\"6d3938bd-8824-424b-bd3a-4d5af717c4b1\":\"yaGLieUJ61BfG6Ef6ixnVP4YJGGqudFFF48fE60o7UE0lKsX2SFal2uc6+AiEM+eSF4Y7VSQqJIEgib7YUqrcQ==\",\"98d97168-9c75-44c5-b631-d1f0e0b5b64b\":\"xDvGwby0mnnf8ZVyNubK26cjAUY19OSEYVaIsmiaQ08eyY4O0ClGeCkxwKYVhY116rjfPxFgVeAIsR62aMIj0g==\",\"b88185e6-2008-4bf6-bb5f-09a6a5e3123c\":\"KYFK3G4IcD3DPKep582HvxG6oaJmh0Sga8Wj2sDsBhWodplX3Xm+p/fVMRLidwi8XMy3Nggii9a+SBxe2OBNvg==\",\"38662a70-39d3-4cb8-829f-c8c662e5d045\":\"WgmSvorXxWW0hM2RdXVwAlmo7JAzFFtdFmSvjXdXFlZb6x266NZvzj5ua6NsYQUpS1/qiJCRSUw7uihOcNNlig==\"}");
        properties.setProperty("keys.role", "{\"manager\":\"6d3938bd-8824-424b-bd3a-4d5af717c4b1\",\"admin\":\"dd55d052-0cc8-4d6d-9057-f3c0c8e47b4e\",\"user\":\"38662a70-39d3-4cb8-829f-c8c662e5d045\",\"refresh_manager\":\"b88185e6-2008-4bf6-bb5f-09a6a5e3123c\",\"refresh_admin\":\"98d97168-9c75-44c5-b631-d1f0e0b5b64b\"}");

        properties.setProperty("app.user.expire.time", "3600000");
        properties.setProperty("app.admin.expire.time", "3600000");
        properties.setProperty("app.manager.expire.time", "3600000");
        properties.setProperty("app.refresh.expire.time", "3600000");
        properties.setProperty("swagger.enabled", "false");

        pspc.setProperties(properties);
        return pspc;
    }

    @Bean
    public JwtTokenProvider getJwtTokenProvider() {
        return new JwtTokenProvider();
    }

}
