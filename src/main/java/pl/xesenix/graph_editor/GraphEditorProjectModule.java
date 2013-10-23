
package pl.xesenix.graph_editor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


public class GraphEditorProjectModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		Locale locale = new Locale("pl", "PL");
		bind(String.class).annotatedWith(Names.named("editor.fxml")).toInstance("/fxml/editor.fxml");
		bind(Locale.class).toInstance(locale);
		bind(ResourceBundle.class).annotatedWith(Names.named("application")).toInstance(ResourceBundle.getBundle("bundles.application", locale));
		bind(ResourceBundle.class).annotatedWith(Names.named("editor.fxml")).toInstance(ResourceBundle.getBundle("bundles.editor", locale));
	}


	@Provides
	public GraphEditorController getEditorController(@Named("editor.fxml") String url, @Named("editor.fxml") ResourceBundle bundle)
	{
		GraphEditorController controller = (GraphEditorController) loadController(url, bundle);
		
		return controller;
	}


	protected Object loadController(String url, ResourceBundle bundle)
	{
		InputStream fxmlStream = null;
		try
		{
			fxmlStream = getClass().getResourceAsStream(url);
			FXMLLoader loader = new FXMLLoader();
			loader.setResources(bundle);
			loader.load(fxmlStream);
			return loader.getController();
		}
		catch (Exception e)
		{
			// FXML load exceptions are really system failures, and can be
			// treated as RuntimeExceptions
			throw new RuntimeException(String.format("Error loading FXML file '%s'", url), e);
		}
		finally
		{
			if (fxmlStream != null)
			{
				try
				{
					fxmlStream.close();
				}
				catch (IOException e)
				{
					System.out.println("Warning: failed to close FXML file");
				}
			}
		}
	}

}
