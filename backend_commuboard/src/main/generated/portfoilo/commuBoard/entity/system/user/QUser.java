package portfoilo.commuBoard.entity.system.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1276930187L;

    public static final QUser user = new QUser("user");

    public final portfoilo.commuBoard.entity.QCommonEntity _super = new portfoilo.commuBoard.entity.QCommonEntity(this);

    public final ComparablePath<Character> admYn = createComparable("admYn", Character.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDtm = _super.createDtm;

    //inherited
    public final StringPath createId = _super.createId;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> loginFailCnt = createNumber("loginFailCnt", Integer.class);

    public final DatePath<java.time.LocalDate> pwModYmd = createDate("pwModYmd", java.time.LocalDate.class);

    public final StringPath salt = createString("salt");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDtm = _super.updateDtm;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final StringPath userId = createString("userId");

    public final StringPath userNick = createString("userNick");

    public final StringPath userPw = createString("userPw");

    public final ComparablePath<Character> useYn = createComparable("useYn", Character.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

