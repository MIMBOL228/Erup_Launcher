package sample;

import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="Tesla"
    private Button Tesla; // Value injected by FXMLLoader

    @FXML // fx:id="Vam"
    private Button Vam; // Value injected by FXMLLoader

    @FXML // fx:id="gos"
    private Button gos; // Value injected by FXMLLoader

    @FXML // fx:id="din"
    private Label din; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="vanila"
    private ImageView vanila; // Value injected by FXMLLoader


    public String server = "Vanilla";
    public String namqe;

    public static String md5(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();

        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        /*gos.setOnAction(event -> {
            String names = login.getText().trim();
            String pass = password.getText().trim();

            String output = getUrlContent("http://localhost/ttt.php?pa=" + md5(pass) + "&lo=" + names + "&server=" + server);
            din.setText(output);
            if(!output.isEmpty()){
                try {
                    JSONObject userJson = new JSONObject(output);
                    namqe = userJson.getString("name");
                    din.setText(String.valueOf(namqe));
                    System.out.println(namqe);
                }catch (Exception e){
                    namqe = output;
                    din.setText(String.valueOf(namqe));
                    System.out.println(namqe);
                }
            }
        });*/
        Vam.setOnAction(event -> {
            server = "Vanilla";
        });
        Tesla.setOnAction(event -> {
            server = "Tesla";
        });

    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try{
         URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e){
           System.out.println("Отмена, оно заболел!");
        }
        return content.toString();
    }
}
