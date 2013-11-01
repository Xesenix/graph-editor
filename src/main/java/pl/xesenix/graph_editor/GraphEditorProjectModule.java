
package pl.xesenix.graph_editor;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import pl.xesenix.slf4j.inject.Slfj4TypeListener;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


public class GraphEditorProjectModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		Locale locale = new Locale("pl", "PL");
		bind(Locale.class).toInstance(locale);
		bind(ResourceBundle.class).annotatedWith(Names.named("application")).toInstance(ResourceBundle.getBundle("bundles.application", locale));
		bind(ResourceBundle.class).annotatedWith(Names.named("project")).toInstance(ResourceBundle.getBundle("bundles.project", locale));
		bind(ResourceBundle.class).annotatedWith(Names.named("about")).toInstance(ResourceBundle.getBundle("bundles.about", locale));
		bindListener(Matchers.any(), new Slfj4TypeListener());
	}
	
	
	@Provides
	@Named("about")
	protected Stage getAbout(GuiceFXMLLoader fxmlLoader, @Named("application") ResourceBundle appResources, @Named("about") ResourceBundle fxmlResources)
	{
		try
		{
			final Parent root = fxmlLoader.load(getClass().getResource("/fxml/about.fxml"), fxmlResources).getRoot();
			
			return StageBuilder
				.create()
				.title(appResources.getString("app.name")).resizable(false)
				.scene(SceneBuilder
					.create()
					.root(root)
					.stylesheets("/styles/styles.css")
					.build()
				)
				.icons(new Image(appResources.getString("app.icon")))
				.build();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
