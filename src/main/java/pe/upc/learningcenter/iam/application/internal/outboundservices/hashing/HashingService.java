package pe.upc.learningcenter.iam.application.internal.outboundservices.hashing;

public interface HashingService {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
