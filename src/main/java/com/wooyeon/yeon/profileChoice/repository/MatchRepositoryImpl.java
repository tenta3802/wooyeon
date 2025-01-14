package com.wooyeon.yeon.profileChoice.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wooyeon.yeon.profileChoice.dto.RecommandUserCondition;
import com.wooyeon.yeon.profileChoice.dto.RecommandUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.wooyeon.yeon.user.domain.QProfile.profile;

/**
 * 모든 유저들의 Profile을 조회합니다.
 *
 * @author heesoo
 */
public class MatchRepositoryImpl implements MatchRepositoryRecommandUserList {

    private final JPAQueryFactory queryFactory;

    public MatchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * Profile 리스트를 조회합니다. 프로퍼티 접근 - setter 방식입니다.
     *
     * @param condition
     * @param pageable
     * @return Page(RecommandUserDto)
     */
    @Override
    public Page<RecommandUserDto> searchUserProfileSimple(RecommandUserCondition condition, Pageable pageable) {
        QueryResults<RecommandUserDto> results = queryFactory
                .select(Projections.bean(RecommandUserDto.class,
                        profile.gender,
                        profile.nickname,
                        profile.birthday,
                        profile.gpsLocationInfo,
                        profile.mbti,
                        profile.intro,
                        profile.user.userCode
                ))
                .from(profile)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<RecommandUserDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
