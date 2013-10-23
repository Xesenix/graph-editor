
package pl.xesenix.graph_editor;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

@Singleton
public class GraphEditorApp extends Application
{

	private static final Logger log = LoggerFactory.getLogger(GraphEditorApp.class);
	
	
	@Inject
	@Named("application")
	private ResourceBundle resources;


	public static void main(String[] args) throws Exception
	{
		launch(args);
	}


	public void start(Stage stage) throws Exception
	{
		Injector injector = Guice.createInjector(new GraphEditorProjectModule());
		
		resources = injector.getInstance(Key.get(ResourceBundle.class, Names.named("application")));
		
		GraphEditorController controller = injector.getInstance(GraphEditorController.class);

		Scene scene = new Scene((Parent) controller.getView(), 1240, 860);
		scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle(resources.getString("app.name"));
		stage.getIcons().add(new Image(resources.getString("app.icon")));
		stage.setScene(scene);
		stage.show();
	}
}
