package site.fish119.adminsadp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.fish119.adminsadp.domain.sys.Authority;
import site.fish119.adminsadp.domain.sys.Role;
import site.fish119.adminsadp.domain.sys.User;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:28
 * @Version V1.0
 */
public class UserDetailsImple implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;
    private final Date createTime;
    private final Date updateTime;

    public UserDetailsImple(Long id, String username, String password,
                            Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate,Date createTime,Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserDetailsImple(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        Set<Authority> authorities = new HashSet<>();
        for(Role role : user.getRoles()){
            authorities.addAll(role.getAuthorities());
        }
        this.authorities = authorities;
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 自定义，返回上次密码重置日期
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Long getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}
