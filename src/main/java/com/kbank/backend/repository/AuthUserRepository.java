package com.kbank.backend.repository;

import com.kbank.backend.domain.AuthUser;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    //처방전 create용 메서드 -김성헌
    @Query("SELECT au FROM AuthUser au WHERE au.socialId = :id AND au.provider = :provider")
    Optional<UserSecurityForm> findBySocialIdAndEProvider(@Param("id") String id, @Param("provider") Provider provider);

    interface UserSecurityForm {

        static UserSecurityForm invoke(AuthUser authUser) {

            return new UserSecurityForm() {
                @Override
                public Long getAuthUserPk() {
                    return authUser.getAuthUserPk();
                }

                @Override
                public Role getRole() {
                    return authUser.getRole();
                }
            };
        }

        Long getAuthUserPk();
        Role getRole();
    }
}
