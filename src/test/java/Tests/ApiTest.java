package Tests;

import Steps.ApiSteps;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {
    private  String userFirstName = "Ivan";
    private  String userLastName = "Olololo";
    private String keyFirstName = "FIRSTNAME";
    private String keyLastName = "LASTNAME";
    private String keyUserId = "ID";
    private String userFirstNameUp = "Nikola";
    private String userLastNameUp = "Tesla";

    /**
     * 1) Создать пользователя
     * 2) Проверить код ответа
     * 3) Проверить тело ответа
     * @throws IOException
     */
    @Test
    public  void createUserMethodTest() throws IOException {
        ApiSteps steps = new ApiSteps();
        String response = steps.createUser(userFirstName,userLastName);
        JSONObject user = new JSONArray(response).getJSONObject(0);

        Assert.assertEquals("firstName",userFirstName,user.getString(keyFirstName));
        Assert.assertEquals("lastName",userLastName,user.getString(keyLastName));
    }

    /**
     * 1) Создать пользователя
     * 2) Вызвать метод Get с id созданного пользователя
     * 3) Првоерить код ответа
     * 4) Проверить тело ответа
     * @throws IOException
     */
    @Test
    public void getUserByIdMethodTest() throws IOException {
        ApiSteps steps = new ApiSteps();
        String response = steps.createUser(userFirstName,userLastName);
        String userId = new JSONArray(response).getJSONObject(0).getString(keyUserId);

        String responseUserById = steps.getUserById(userId);
        JSONObject userById = new JSONArray(responseUserById).getJSONObject(0);

        Assert.assertEquals("firstName",userFirstName,userById.getString(keyFirstName));
        Assert.assertEquals("lastName",userLastName,userById.getString(keyLastName));
    }

    /**
     * 1) Создать пользователя
     * 2) Вызвать метод PUT с id созданного пользователя
     * 3) Првоерить код ответа
     * 4) Проверить тело ответа
     * @throws IOException
     */
    @Test
    public void updateUserDataMethodTest() throws IOException {
        ApiSteps steps = new ApiSteps();
        String response = steps.createUser(userFirstName,userLastName);
        String userId = new JSONArray(response).getJSONObject(0).getString(keyUserId);

        steps.updateUserData(userId,userFirstNameUp,userLastNameUp);

        String responceUserById = steps.getUserById(userId);

        JSONObject userAfterUpdate = new JSONArray(responceUserById).getJSONObject(0);
        Assert.assertEquals("firstName",userFirstNameUp,userAfterUpdate.getString(keyFirstName));
        Assert.assertEquals("lastName",userLastNameUp,userAfterUpdate.getString(keyLastName));
    }
    /**
     * 1) Создать пользователя
     * 2) Вызвать метод DELETE с id созданного пользователя
     * 3) Првоерить код ответа
     * 4) Вызвать метод Get c id пользователя.
     * 5) Проверить что пользователь удален.
     * @throws IOException
     */
    @Test
    public void deleteUserMethodTest() throws IOException {
        ApiSteps steps = new ApiSteps();
        String response = steps.createUser(userFirstName,userLastName);
        String userId = new JSONArray(response).getJSONObject(0).getString(keyUserId);

        steps.deleteUser(userId);

        String responseDeletedUser = steps.getUserById(userId);

        Assert.assertEquals("[]",responseDeletedUser);
    }

    /**
     * 1) Вызвать метод GET
     * 2) Удалить всех пользователей
     * 3) Создать N пользователей
     * 4) Проверить что пользователи общее кол-во.
     * @throws IOException
     */
    @Test
    public void getAllUsersMethodTest() throws IOException {
        ApiSteps steps = new ApiSteps();
        int numOfUsersBefore = new JSONArray(steps.getAllUsers()).length();

        steps.deleteN_Users(numOfUsersBefore);

        int numOfCratedUsers = 10;
        steps.createN_Users(userFirstName,userLastName,numOfCratedUsers);

        int numOfUsersAfter = new JSONArray(steps.getAllUsers()).length();

        Assert.assertTrue(numOfUsersAfter-numOfUsersBefore==numOfCratedUsers);
    }

}
