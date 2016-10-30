
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class JavaFXKeyEventExample extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        //create a console for logging key events
        final ListView<String> console = new ListView<String>(FXCollections.<String>observableArrayList());
        // listen on the console items and remove old ones when we get over 10 items
        console.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                while (change.next()) {
                    if (change.getList().size() > 20) {
                        change.getList().remove(0);
                    }
                }
            }
        });
        // create text box for typing in
        final TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        textBox.setStyle("-fx-font-size: 34;");
        //add a key listeners
        textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                
                console.getItems().add("Key Pressed: " + ke.getText());
            }
        });
        textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                console.getItems().add("Key Released: " + ke.getText());
            }
        });
        textBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String text = "Key Typed: " + ke.getCharacter();
                if (ke.isAltDown()) {
                    text += " , alt down";
                }
                if (ke.isControlDown()) {
                    text += " , ctrl down";
                }
                if (ke.isMetaDown()) {
                    text += " , meta down";
                }
                if (ke.isShiftDown()) {
                    text += " , shift down";
                }
                console.getItems().add(text);
            }
        });
        VBox vb = new VBox(10);
        vb.getChildren().addAll(textBox, console);
        root.getChildren().add(vb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
