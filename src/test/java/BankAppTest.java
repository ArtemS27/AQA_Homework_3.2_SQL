import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import ru.netology.data.SQL_Helper;
import static com.codeborne.selenide.Selenide.*;

public class BankAppTest {

    @AfterAll
    static void tearDown(){
        //SQL_Helper.cleanData();
    }

    @Test
    void shouldLogIn(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = UserData.getAuthInfoWithData();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQL_Helper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldThrowMessageIfLoginWithRandomUser(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = UserData.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.findErrorMessage();
    }

    @Test
    void shouldThrowMessageIfLoginWithExistUserAndRandomVerificationCode(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = UserData.getAuthInfoWithData();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.generateVerificationCode();
        verificationPage.verify(verificationCode);
        verificationPage.verifyErrorNotificationVisibility();
    }
}
