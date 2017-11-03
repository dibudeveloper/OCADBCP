package uni.ocad.bcp.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 @Autowired
 DataSource dataSource;
 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
   auth.jdbcAuthentication().dataSource(dataSource)
   .passwordEncoder(passwordEncoder())
  .usersByUsernameQuery(
   "select username, password, is_active as enabled from tb_user where username=?")
  .authoritiesByUsernameQuery(
   "select u.username, r.role from tb_user u, tb_role r, tb_user_role ur where u.id_user=ur.id_user and r.id_role=ur.id_role and u.username=?");
 } 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
   http.authorizeRequests()
//   .antMatchers("/login*").anonymous()
   .antMatchers("/SISTEMA").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

  
  .anyRequest().permitAll()
  .and()
    .formLogin().loginPage("/login").defaultSuccessUrl("/InicioSimulacro")
    .usernameParameter("username").passwordParameter("password")
  .and()
    .logout().logoutSuccessUrl("/login?logout")
   .and()
   .exceptionHandling().accessDeniedPage("/403")
  .and()
    .csrf().disable();
 }
 
 	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}