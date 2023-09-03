package com.example.ticketingsystem.Config;


import com.example.ticketingsystem.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/adminRegister","/api/v1/auth/managerRegister","/api/v1/auth/systemOwnerRegister").permitAll()
                .requestMatchers("/api/v1/manager/getAllManagers","/api/v1/owner/getAllOwners","/api/v1/ticket/getAllTickets","/api/v1/getAllResults","/api/v1/request/deleteRequest/{request_id}","/api/v1/ticket/deleteTicket/{ticket_id}","/api/v1/result/deleteResult/{result_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/manager/addProfile","/api/v1/manager/updateInfo","/api/v1/manager/deleteAccount","/api/v1/ticket/addTicket/{request_id}", "/api/v1/ticket/updateTicket/{ticket_id}","/api/v1/ticket/getMyTickets","/api/v1/request/getAllRequests","/api/v1/request/managerAcceptRequest/{request_id}","/api/v1/request/getAllResults","/api/v1/request/addResult/{request_id}","/api/v1/request/updateResult/{request_id}").hasAuthority("MANAGER")
                .requestMatchers("/api/v1/owner/addProfile","/api/v1/owner/updateInfo","/api/v1/owner/deleteAccount","/api/v1/request/getOwnerRequest","/api/v1/request/makeRequest","/api/v1/request/reRequest/{request_id}").hasAuthority("OWNER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
