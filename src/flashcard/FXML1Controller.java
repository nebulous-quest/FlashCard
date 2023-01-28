package flashcard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sajid
 */
public class FXML1Controller implements Initializable {

    @FXML
    private TextArea question;
    @FXML
    private TextArea answer;

    ArrayList<Card> cardList=new ArrayList<Card>();
    
    FileChooser fileChooser = new FileChooser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.setInitialDirectory(new File("G:/Spring21Java/FlashCard/src/flashcard/TextFile"));
    }    

    @FXML
    private void home(ActionEvent event) {
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
    private void nextC(ActionEvent event) {
        Card card = new Card(question.getText(),answer.getText());
        cardList.add(card);
        question.clear();
        answer.clear();
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file =fileChooser.showSaveDialog(primaryStage);
        if(file!=null) SaveSystem(file);
    }

    public void SaveSystem(File file) throws FileNotFoundException, IOException{
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Iterator<Card> cardIterator= cardList .iterator();
        while(cardIterator.hasNext()){
            Card c=(Card)cardIterator.next();
            writer.write(c.getQuestion()+"\n");
            writer.write(c.getAnswer()+"\n");            
        }
        writer.close();
    }
}
