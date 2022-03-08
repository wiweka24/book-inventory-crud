package bookinventory.crud;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
  public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "budi";
    String encodedPassword = encoder.encode(rawPassword);
    System.out.println(encodedPassword);
  }
}
