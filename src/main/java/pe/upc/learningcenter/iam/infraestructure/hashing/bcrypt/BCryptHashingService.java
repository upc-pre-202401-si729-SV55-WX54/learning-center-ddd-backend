package pe.upc.learningcenter.iam.infraestructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import pe.upc.learningcenter.iam.application.internal.outboundservices.hashing.HashigService;

public interface BCryptHashingService extends HashigService, PasswordEncoder {

}
