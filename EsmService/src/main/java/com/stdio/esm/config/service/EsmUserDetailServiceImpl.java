package com.stdio.esm.config.service;



import com.stdio.esm.exception.EsmException;
import com.stdio.esm.mapper.AccountMapper;
import com.stdio.esm.model.Account;
import com.stdio.esm.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Service
public class EsmUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username).orElseThrow(() -> new EsmException(EsmException.USER_NOT_FOUND));
        if (account.getDeleteFlag()) {
            throw new EsmException(EsmException.BAD_REQUEST);
        }
        List<GrantedAuthority> authorityList = account.getAccountRoleList()
                .stream().map(tmp->new SimpleGrantedAuthority(tmp.getRole().getName()))
                .collect(Collectors.toList());
        return AccountMapper.INSTANCE.toAccountDetail(account,authorityList);
    }
}
