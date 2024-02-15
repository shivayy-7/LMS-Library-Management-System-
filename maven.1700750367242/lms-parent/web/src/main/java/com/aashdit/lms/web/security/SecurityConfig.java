package com.aashdit.lms.web.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aashdit.umt.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.aashdit.*"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off

        http.headers() // Security Fix #3 - Vishnoo
                .contentTypeOptions()
                .and()
                .xssProtection()
                .and()
                .cacheControl()
                .and()
                .httpStrictTransportSecurity()
                .and()
                .frameOptions()
                .and()
                .contentSecurityPolicy("script-src 'self' 'unsafe-inline'")
                .and()
                .referrerPolicy(ReferrerPolicy.SAME_ORIGIN);

        http.authorizeRequests()
                .antMatchers(HttpMethod.TRACE, "/**").denyAll() // Security Fix #2 - Vishnoo
                .antMatchers(HttpMethod.PATCH, "/**").denyAll().antMatchers(HttpMethod.PUT, "/**").denyAll()
                .antMatchers(HttpMethod.DELETE, "/**").denyAll().antMatchers(HttpMethod.HEAD, "/**").denyAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
               // .antMatchers("/umt/login").permitAll()
                .antMatchers("/overwrite/umt/login").permitAll()
                .antMatchers("/captcha/**").permitAll()
                .antMatchers("/assetsLogin/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                //.antMatchers("/forgotPassword.htm").permitAll()
                // .antMatchers("/password").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/system/**").hasAnyRole("SYSTEM")
                .antMatchers("/umt/user/add").hasAnyRole("ADMIN").antMatchers("/umt/user/list").hasAnyRole("ADMIN")
                .antMatchers("/admin/**").hasAnyRole("SYSTEM", "ADMIN")
                .antMatchers("/public/**").permitAll()
                .antMatchers("/reports/**").permitAll()
                .antMatchers("/webapi/**").permitAll()
                .antMatchers("/mst/add-member/**").permitAll()
                .antMatchers("/add-member-data/**").permitAll()
                .antMatchers("/mst/save-member/**").permitAll()
                .antMatchers("/save-member-data/**").permitAll()
                .antMatchers("/mst/duplicate_aadhar_check/**").permitAll()
                .antMatchers("/mst/emailCheck/**").permitAll()
                
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                //.logoutUrl("/umt/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true).deleteCookies("RENT_OF_THE_ONE_SHOP", "JSESSIONID").permitAll();

        // http.csrf().disable().addFilterAfter(new
        // NonceCSRFFilter(httpSessionCsrfTokenRepository()), CsrfFilter.class);
        http.cors().and().csrf().ignoringAntMatchers("/api/**", "/api/login,/webapi/**","/public/**");

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // @formatter:on
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /*
	 * @Bean public AccessDecisionManager accessDecisionManager() {
	 * List<AccessDecisionVoter<? extends Object>> decisionVoters = new
	 * ArrayList<AccessDecisionVoter<? extends Object>>(); decisionVoters.add(new
	 * WebExpressionVoter()); decisionVoters.add(new RoleVoter());
	 * decisionVoters.add(new AuthenticatedVoter()); return new
	 * UnanimousBased(decisionVoters); }
	 * 
     */
    
}
