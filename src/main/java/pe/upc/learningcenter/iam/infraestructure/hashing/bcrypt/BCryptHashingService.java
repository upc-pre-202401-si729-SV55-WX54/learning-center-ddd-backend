package pe.upc.learningcenter.iam.infraestructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import pe.upc.learningcenter.iam.application.internal.outboundservices.hashing.HashingService;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
