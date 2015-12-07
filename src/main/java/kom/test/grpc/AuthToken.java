package kom.test.grpc;

/**
 * Created by Sergey on 06.12.2015
 */
public class AuthToken {
    private static final ThreadLocal<String> AUTH_TOKEN = new ThreadLocal<>();

    /**
     * Constructor.
     */
    private AuthToken() { }

    /**
     * Sets the user ID.
     * @param userId the authenticated user ID.
     */
    public static void set(final String userId) {
        AUTH_TOKEN.set(userId);
    }

    /**
     * Deletes the user ID.
     */
    public static void remove() {
        AUTH_TOKEN.remove();
    }

    /**
     * Returns the user ID.
     * @return the user ID.
     */
    public static String get() {
        return AUTH_TOKEN.get();
    }
}
