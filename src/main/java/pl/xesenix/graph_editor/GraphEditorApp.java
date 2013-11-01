
package pl.xesenix.graph_editor;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.xesenix.slf4j.inject.InjectLogger;

import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.cathive.fx.guice.GuiceFXMLLoader.Result;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


@Singleton
public class GraphEditorApp extends GuiceApplication
{
	@InjectLogger
	private Logger log;


	@Inject
	private GuiceFXMLLoader fxmlLoader;


	@Inject
	@Named("application")
	private ResourceBundle resources;


	public static void main(String[] args) throws Exception
	{
		launch(args);
	}


	public void start(final Stage stage) throws Exception
	{
		log.debug("application start");
		
		final AppController controller = (AppController) fxmlLoader.load(getClass().getResource("/fxml/application.fxml"), resources).getController();
		
		StageBuilder
			.create()
			.title(resources.getString("app.name")).resizable(false)
			.scene(SceneBuilder
				.create()
				.root(controller.getView())
				.stylesheets("/styles/styles.css")
				.build()
			)
			.icons(new Image(resources.getString("app.icon")))
			.resizable(true)
			.width(1200)
			.height(800)
			.applyTo(stage);
		
		stage.show();
	}


	@Override
	public void init(List<Module> modules) throws Exception
	{
		modules.add(new GraphEditorProjectModule());
	}
}
