package pe.upc.learningcenter.iam.application.internal.outbounservices.hashing;

public interface HashingService {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
