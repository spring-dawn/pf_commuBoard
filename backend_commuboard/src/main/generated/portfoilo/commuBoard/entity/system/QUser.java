package portfoilo.commuBoard.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -713561020L;

    private static final PathInits INITS = PathInits.DIRECT2;

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

    public final DateTimePath<java.time.LocalDateTime> quitDtm = createDateTime("quitDtm", java.time.LocalDateTime.class);

    public final NumberPath<Integer> reportCnt = createNumber("reportCnt", Integer.class);

    public final QRole role;

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
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

