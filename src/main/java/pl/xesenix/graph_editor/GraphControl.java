
package pl.xesenix.graph_editor;

import java.io.IOException;

import org.slf4j.Logger;

import pl.xesenix.slf4j.inject.InjectLogger;

import com.google.inject.Inject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class GraphControl extends VBox
{
	
	
	@Inject
	public GraphControl(Controller controller)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/graph.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(controller);
		
		try
		{
			fxmlLoader.load();
		}
		catch (IOException exception)
		{
			throw new RuntimeException(exception);
		}
	}
	
	
	public static class Controller {
		@InjectLogger
		private Logger log;
		
		
		@FXML
		private Pane viewport;
		
		
		public void initialize()
		{
			log.debug("Initializing graph view");
			viewport.getChildren().addAll(new Circle(100, 100, 20), new Circle(200, 100, 20), new Circle(140, 170, 20));
		}
	}
}
