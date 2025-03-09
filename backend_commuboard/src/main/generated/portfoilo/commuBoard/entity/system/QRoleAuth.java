package portfoilo.commuBoard.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleAuth is a Querydsl query type for RoleAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoleAuth extends EntityPathBase<RoleAuth> {

    private static final long serialVersionUID = 1132315447L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleAuth roleAuth = new QRoleAuth("roleAuth");

    public final QAuth auth;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRole role;

    public QRoleAuth(String variable) {
        this(RoleAuth.class, forVariable(variable), INITS);
    }

    public QRoleAuth(Path<? extends RoleAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoleAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoleAuth(PathMetadata metadata, PathInits inits) {
        this(RoleAuth.class, metadata, inits);
    }

    public QRoleAuth(Class<? extends RoleAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auth = inits.isInitialized("auth") ? new QAuth(forProperty("auth"), inits.get("auth")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

