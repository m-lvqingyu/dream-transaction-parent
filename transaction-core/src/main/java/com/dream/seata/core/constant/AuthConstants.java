package com.dream.seata.core.constant;

/**
 * @author Lv.qingyu
 */
public class AuthConstants {

    /**
     * JWT存储权限前缀
     */
    private static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    private static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 认证信息Http请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * JWT载体key
     */
    private static final String JWT_PAYLOAD_KEY = "payload";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_KEY = "auth:resourceRoles";

    private static final String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    public static final String CLIENT_DETAILS_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    public static final String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from oauth_client_details";
    public static final String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";
    public static final String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";

    private static final String BCRYPT = "{bcrypt}";

    private static final String JWT_USER_ID_KEY = "id";

    private static final String JWT_CLIENT_ID_KEY = "client_id";
}
