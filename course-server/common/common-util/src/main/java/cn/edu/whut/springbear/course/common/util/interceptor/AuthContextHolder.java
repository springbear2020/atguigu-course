package cn.edu.whut.springbear.course.common.util.interceptor;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:43
 */
public class AuthContextHolder {
    /**
     * 后台管理员用户 id
     */
    private static final ThreadLocal<Long> ADMIN = new ThreadLocal<>();
    /**
     * 普通用户 id
     */
    private static final ThreadLocal<Long> USER = new ThreadLocal<>();

    public static Long getAdminId() {
        return ADMIN.get();
    }

    public static void setAdminId(Long adminId) {
        ADMIN.set(adminId);
    }

    public static Long getUserId() {
        return USER.get();
    }

    public static void setUserId(Long userId) {
        USER.set(userId);
    }
}

