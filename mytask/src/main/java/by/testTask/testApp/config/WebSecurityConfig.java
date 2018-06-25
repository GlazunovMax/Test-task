package by.testTask.testApp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//import by.testTask.testApp.service.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//@Autowired
	//private UserService userService;
	//.antMatchers("/", "/registration").permitAll()
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/mainUser").hasRole("USER")
                .antMatchers("/mainAdmin", "/statistics", "/showAllApps/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("admin")
                   .roles("ADMIN")
                   .build();
        
        return new InMemoryUserDetailsManager(user, admin);
        
	
    }
    
   /* @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("user").roles("USER")
                    .and()
                    .withUser("admin").password("admin").roles("ADMIN");
                   
        }
    }*/
    
   // @Override
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   // 	auth.userDetailsService(userService)
   // 		.passwordEncoder(NoOpPasswordEncoder.getInstance());
    		
  //  }
    
    /*
    ///////////////////////////
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		.passwordEncoder(NoOpPasswordEncoder.getInstance())
    		.usersByUsernameQuery("SELECT username, password, active FROM user WHERE username=?")
    		.authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM user INNER JOIN user_role ur on u.id = ur.user_id WHERE u.username=?");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}

