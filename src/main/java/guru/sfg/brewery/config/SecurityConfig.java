package guru.sfg.brewery.config;

import guru.sfg.brewery.security.MyPasswordEncoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/beer/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password("{bcrypt}$2a$15$5hwEurkLgors7jKBJDkRtOSP6jvdukhjXBj/vnJgHJ8oLIz5bP0wy")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{ldap}{SSHA}QjYuPcm0onV3t//sEJ6OP48WUJEnBOYfhgeJDQ==")
                .roles("USER")
                .and()
                .withUser("ramesh")
                .password("{sha256}cdbfca20ed357524d491db1b0df752059703c6de1dded9bf46a8a6df61e12f2bc581980048e2311e")
                .roles("USER");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return MyPasswordEncoderFactory.createDelegatingPasswordEncoder();
    }

    /*  @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("spring")
                .password("guru")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }*/


}
