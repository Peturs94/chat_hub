package is.hi.hbv501g.chathub.Chathub.Services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}