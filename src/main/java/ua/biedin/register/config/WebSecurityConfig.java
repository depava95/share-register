package ua.biedin.register.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.biedin.register.security.JwtUserDetailsService;
import ua.biedin.register.security.jwt.JwtConfigurer;
import ua.biedin.register.security.jwt.JwtTokenProvider;
import ua.biedin.register.util.Constants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {


    private String PATH_PRIVATE = "/api/v1/private/**";

    private String PATH_PUBLIC = "/api/v1/public/**";

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService customUserDetailsService;

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider,
                             JwtUserDetailsService customUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("api/v1/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }




    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPassword() {
        return new BCryptPasswordEncoder(11);
    }
}
