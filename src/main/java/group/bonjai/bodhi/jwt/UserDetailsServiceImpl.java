package group.bonjai.bodhi.jwt;

import group.bonjai.bodhi.models.Admin;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.models.Roles;
import group.bonjai.bodhi.repositories.AdminRepository;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DepartmentMemberRepository departmentMemberRepository;
    private final AdminRepository adminRepository;

    public UserDetailsServiceImpl(DepartmentMemberRepository departmentMemberRepository, AdminRepository adminRepository) {
        this.departmentMemberRepository = departmentMemberRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
        Optional<DepartmentMember> optionalDepartmentMember = departmentMemberRepository.findByEmail(email);

        if(optionalAdmin.isPresent()){
           return new User(optionalAdmin.get().getEmail(), optionalAdmin.get().getPassword(),
                   Collections.singletonList(new SimpleGrantedAuthority(Roles.ADMIN)));
       }

       else if(optionalDepartmentMember.isPresent()){
           DepartmentMember departmentMember = optionalDepartmentMember.get();
           return new User(departmentMember.getEmail(),
                   departmentMember.getPassword(),
                   Collections.singletonList(new SimpleGrantedAuthority(departmentMember.getRole())));
       }
       //TODO : check for student
       throw new UsernameNotFoundException("Email "+email+" Not Found");

    }
}