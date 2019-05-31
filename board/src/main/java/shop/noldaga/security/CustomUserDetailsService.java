package shop.noldaga.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import shop.noldaga.domain.CustomUser;
import shop.noldaga.domain.MemberVo;
import shop.noldaga.mapper.MemberMapper;

@Log4j @Setter @Component
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("loadUserByUsername : " + username);
		MemberVo vo = mapper.read(username);
		return vo == null ? null : new CustomUser(vo);
	}
	
}
