package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // xác thực
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    // phân quyền
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         cấu hình có thể logout
        http.csrf(AbstractHttpConfigurer::disable);
        // tạo token cho method post
//        http.csrf(Customizer.withDefaults());
//        http.csrf((csrf) -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                );
        // các đường dẫn không phải login
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/blogs", "/login","/logoutSuccessful", "/logoutSuccessful", "/403").permitAll());
        // cấp quyền cho user
        http.authorizeHttpRequests((authorize) -> authorize
                // Quy tắc cho phép cả USER và ADMIN truy cập vào các trang cần thiết
                .requestMatchers("/blogs", "/blogs/search", "/blogs/create", "/blogs/edit/**", "/blogs/create?continue", "/userInfo")
                .hasAnyRole("USER", "ADMIN") // Cả USER và ADMIN đều có thể truy cập

                // Quy tắc cho phép ADMIN truy cập vào các trang liên quan đến admin và quản lý bài viết
                .requestMatchers("/admin", "/blogs/delete/**").hasRole("ADMIN") // Chỉ ADMIN có thể truy cập admin và xóa bài viết

                // Quy tắc cho phép ADMIN và USER đều có quyền tạo, chỉnh sửa và xóa bài viết
                .requestMatchers("/blogs/create", "/blogs/edit/**", "/blogs/delete/**")
                .hasAnyRole("USER", "ADMIN")

                // Quy tắc cho phép USER và ADMIN truy cập thông tin người dùng
                .requestMatchers("/userInfo").hasAnyRole("USER", "ADMIN")
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // đường dẫn trùng với url form login
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login")
                .usernameParameter("username")//trùng với tên trong form đăng nhập
                .passwordParameter("password")// trung với tên trong form đăng nhập
        );
        // cấu hình logout
        http.logout(form -> form.logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful"));

        // cấu hình trả về trang 403 khi không có quyền (role) truy cập
        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        // cấu hình remember-me : lưu trạng thái đăng nhập khi tắt trình duyệt => mở lại không cần login
//        http.rememberMe(remember -> remember.tokenRepository(persistentTokenRepository()));
        return http.build();

    }

    // Cấu hình vùng nhớ lưu trạng thái đăng nhập trên bộ nhớ của máy tính người dùng
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }


}