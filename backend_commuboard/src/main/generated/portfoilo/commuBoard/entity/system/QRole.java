package portfoilo.commuBoard.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -713654033L;

    public static final QRole role = new QRole("role");

    public final portfoilo.commuBoard.entity.QCommonEntity _super = new portfoilo.commuBoard.entity.QCommonEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDtm = _super.createDtm;

    //inherited
    public final StringPath createId = _super.createId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final ListPath<RoleAuth, QRoleAuth> roleAuthList = this.<RoleAuth, QRoleAuth>createList("roleAuthList", RoleAuth.class, QRoleAuth.class, PathInits.DIRECT2);

    public final StringPath roleNm = createString("roleNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDtm = _super.updateDtm;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final ComparablePath<Character> useYn = createComparable("useYn", Character.class);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

