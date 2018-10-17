package py.org.ideasweb.solumend.models.seguridad;

/**
 * Created by root on 30/11/16.
 */

public class CredentialValues {

    private static String accessToken="error";
    private static Token tokenData = null;
    private static LoginData loginData = null;

    public static void setAccessTokenString(String accessToken) {
        CredentialValues.accessToken = accessToken;
        CredentialValues.tokenData = null;
        CredentialValues.loginData = null;
    }

    public static void setLoginData(LoginData loginData) {
        CredentialValues.loginData = loginData;
    }

    public static void setAccessToken(Token token) throws Exception {

        accessToken = token.getAccess_token();
        tokenData = token;

    }

    public static String getAccessTokenString(){
        return accessToken;
    }

    public static Token getAccessTokenData(){
        return tokenData;
    }

    public static LoginData getLoginData( ) {return  loginData; }


}
