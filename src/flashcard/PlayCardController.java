package flashcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Sajid
 */
public class PlayCardController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    ArrayList<String> string = new ArrayList<String>();
    private int count = 1;
    @FXML
    private Button showbtn;
    @FXML
    private TextField displayBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileChooser.setInitialDirectory(new File("G:/Spring21Java/FlashCard/src/flashcard/TextFile"));

    }

    @FXML
    private void home1(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit1(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void show(ActionEvent event) throws FileNotFoundException, IOException {
        if (count != string.size()) {
            if (count % 2 == 0 || count == 0) {
                showbtn.setText("Show Answer");
                displayBox.setText(string.get(count));
                count++;

            } else {
                showbtn.setText("Next Card");
                displayBox.setText(string.get(count));
                count++;
            }
        } else {
            showbtn.setDisable(true);
            displayBox.setDisable(true);
        }
    }

    @FXML
    private void choose(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File myObj = fileChooser.showOpenDialog(primaryStage);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                string.add(data);
            }
            displayBox.setText(string.get(0));
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
