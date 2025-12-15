package com.example.bffazure.config;

import feign.RequestTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FeignAuthInterceptorTest {

    @InjectMocks
    private FeignAuthInterceptor interceptor;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication auth;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        SecurityContextHolder.clearContext();
    }

    private JwtAuthenticationToken buildJwtAuth(String tokenValue) {
        Jwt jwt = Jwt.withTokenValue(tokenValue)
                .header("alg", "none")
                .claim("sub", "test-user")
                .build();

        return new JwtAuthenticationToken(jwt);
    }

    @Test
    void apply_securityDisabled_noHeaderAdded() {

        // Desactivamos seguridad vía reflection
        interceptor = new FeignAuthInterceptor();
        TestUtils.setField(interceptor, "feignSecurityEnabled", false);

        RequestTemplate template = new RequestTemplate();

        interceptor.apply(template);

        assertThat(template.headers()).doesNotContainKey("Authorization");
    }

    @Test
    void apply_noAuthentication_noHeaderAdded() {

        when(securityContext.getAuthentication()).thenReturn(null);
        TestUtils.setField(interceptor, "feignSecurityEnabled", true);

        RequestTemplate template = new RequestTemplate();

        interceptor.apply(template);

        assertThat(template.headers()).doesNotContainKey("Authorization");
    }

    @Test
    void apply_authNotJwt_noHeaderAdded() {

        when(securityContext.getAuthentication()).thenReturn(auth);
        TestUtils.setField(interceptor, "feignSecurityEnabled", true);

        RequestTemplate template = new RequestTemplate();

        interceptor.apply(template);

        assertThat(template.headers()).doesNotContainKey("Authorization");
    }

    @Test
    void apply_jwtAuthentication_headerAdded() {

        JwtAuthenticationToken jwtAuth = buildJwtAuth("ABC123TOKEN");

        when(securityContext.getAuthentication()).thenReturn(jwtAuth);
        TestUtils.setField(interceptor, "feignSecurityEnabled", true);

        RequestTemplate template = new RequestTemplate();

        interceptor.apply(template);

        assertThat(template.headers()).containsKey("Authorization");
        assertThat(template.headers().get("Authorization"))
                .containsExactly("Bearer ABC123TOKEN");
    }
}