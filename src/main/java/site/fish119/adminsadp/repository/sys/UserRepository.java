package site.fish119.adminsadp.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.sys.User;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.sys
 * @Author fish119
 * @Date 2018/4/10 14:26
 * @Version V1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>,QuerydslPredicateExecutor<User> {
    User findByUsername(@Param("username") final String username);
    Long countByNickname(@Param("nickname") final String nickname);
    Long countByPhone(@Param("phone") final String phone);
    Long countByEmail(@Param("email") final String email);
}
