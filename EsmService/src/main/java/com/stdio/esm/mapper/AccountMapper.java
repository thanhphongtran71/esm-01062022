package com.stdio.esm.mapper;


import com.stdio.esm.config.service.EsmUserDetail;
import com.stdio.esm.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Mapper
public interface AccountMapper {


    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    /**
     * Convert account and list roles to EsmUserDetail information
     *
     * @param account {@link Account}
     * @param authorityList {@link List<GrantedAuthority>}
     * @return {@link EsmUserDetail}
     */
    @Mappings({
            @Mapping(source = "authorityList", target = "authorities")
    })
    EsmUserDetail toAccountDetail(Account account, List<GrantedAuthority> authorityList);

}
