package org.feed.community_feed.user.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -317169724L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final org.feed.community_feed.common.repository.entity.QTimeBaseEntity _super = new org.feed.community_feed.common.repository.entity.QTimeBaseEntity(this);

    public final NumberPath<Integer> followerCount = createNumber("followerCount", Integer.class);

    public final NumberPath<Integer> followingCount = createNumber("followingCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath profileImage = createString("profileImage");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

