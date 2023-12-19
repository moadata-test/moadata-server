package com.moadataserver.member.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.moadataserver.member.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        Member result = queryFactory.select(member)
                .from(member)
                .where(member.email.eq(email)
                        .and(member.deletedDateTime.isNull()))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        Member result = queryFactory.select(member)
                .from(member)
                .where(member.id.eq(id)
                        .and(member.deletedDateTime.isNull()))
                .fetchOne();
        return Optional.of(result);
    }

    @Override
    public Page<Member> searchMember(Long id, String name, String email, Integer type, PageRequest pageRequest) {
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(
                        eqId(id).and(likeEmail(email))
                                .and(likeName(name))
                                .and(eqType(type))
                                .and(member.deletedDateTime.isNull())
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(member)
                .where(
                        eqId(id).and(likeEmail(email))
                                .and(likeName(name))
                                .and(eqType(type))
                                .and(member.deletedDateTime.isNull())
                )
                .fetch()
                .size();

        return new PageImpl<>(result, pageRequest, total);
    }

    @Override
    public Page<Member> findAllMembers(PageRequest pageRequest) {

        long total = queryFactory.select(member)
                .from(member)
                .where(member.deletedDateTime.isNull())
                .fetch().size();


        List<Member> result = queryFactory.select(member)
                .from(member)
                .where(member.deletedDateTime.isNull())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageRequest, total);
    }

//    private BooleanExpression eqId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        return member.id.eq(id);
//    }
//
//    private BooleanExpression eqName(String name) {
//        if (name == null || name.isBlank()) {
//            return null;
//        }
//        return member.name.eq(name);
//    }
//    private BooleanExpression eqEmail(String email) {
//        if (email == null || email.isBlank()) {
//            return null;
//        }
//        return member.email.eq(email);
//    }
//
//    private BooleanExpression eqType(Integer type) {
//        if (type == null) {
//            return null;
//        }
//        return member.type.eq(type);
//    }

    private BooleanBuilder eqId(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        if (id != null) {
            builder.and(member.id.eq(id));
        }
        return builder;
    }

    private BooleanBuilder likeName(String name) {
        BooleanBuilder builder = new BooleanBuilder();
        if (name != null && !name.isBlank()) {
            builder.and(member.name.like("%" + name + "%"));
        }
        return builder;
    }

    private BooleanBuilder likeEmail(String email) {
        BooleanBuilder builder = new BooleanBuilder();
        if (email != null && !email.isBlank()) {
            builder.and(member.email.like("%" + email + "%"));
        }
        return builder;
    }

    private BooleanBuilder eqType(Integer type) {
        BooleanBuilder builder = new BooleanBuilder();
        if (type != null) {
            builder.and(member.type.eq(type));
        }
        return builder;
    }
}
