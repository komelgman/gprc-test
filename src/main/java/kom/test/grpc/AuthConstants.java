package kom.test.grpc;

import io.grpc.Metadata;

/**
 * Created by Sergey on 06.12.2015
 */
public class AuthConstants {
    public final static Metadata.Key<String> USER_TOKEN_HEADER =
            Metadata.Key.of("user-token", Metadata.ASCII_STRING_MARSHALLER);
}
