package com.cos.gowalk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig  {

//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").authenticated() // 인증된 사용자만 접근 가능
                        .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER") // ROLE_ADMIN 또는 ROLE_MANAGER 권한 필요
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ROLE_ADMIN 권한 필요
                        .anyRequest().permitAll() // 나머지 모든 요청은 허용
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login") // 로그인 처리 URL
                        .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 URL
                );
//                .oauth2Login(oauth2Login -> oauth2Login
//                        .loginPage("/loginForm")
//                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
//                                .userService(principalOauth2UserService) // OAuth2 로그인 사용자 서비스
//                        )
//                );

        return http.build();
    }

//    .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소!!
//                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/loginForm")
//                .loginProcessingUrl("/login")// login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
//                .defaultSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm") //구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드x,(액세스토큰 + 사용자프로필정보 o)
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService)
//                .and().and().build();
}

