package guru.sfg.brewery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

public class PasswordEncodersTest {

    static final String PASSWORD="ramesh";

    @Test
    void testBcrypt(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
        System.out.println("bcrypt : " + passwordEncoder.encode(PASSWORD));
        System.out.println("bcrypt : " + passwordEncoder.encode("guru"));

    }

    @Test
    void testSha256(){
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        System.out.println("sha256 : " + passwordEncoder.encode("padma"));
    }

    @Test
    void testLDAPPassword(){
        PasswordEncoder passwordEncoder = new LdapShaPasswordEncoder();

        System.out.println("LDAP : " + passwordEncoder.encode(PASSWORD));
        System.out.println("LDAP : " + passwordEncoder.encode(PASSWORD));
    }

    @Test
    void testNoOpPassword(){
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        System.out.println(" NoOpPasswordEncoder : " + passwordEncoder.encode(PASSWORD));
    }

    @Test
    void hashingExample(){
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
    }

}
