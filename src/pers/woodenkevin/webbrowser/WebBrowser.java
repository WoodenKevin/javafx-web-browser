package pers.woodenkevin.webbrowser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WebBrowser extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pers/woodenkevin/webbrowser/view/Main.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1000, 750);
        stage.initStyle(StageStyle.TRANSPARENT); // 隐藏默认标题栏
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
