
package pl.xesenix.graph_editor;

import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.slf4j.Logger;

import pl.xesenix.scene.control.cell.SimplePropertyNameValueFactory;
import pl.xesenix.scene.control.cell.SimplePropertyValueFactory;
import pl.xesenix.scene.control.cell.TextBoxCellFactory;
import pl.xesenix.slf4j.inject.InjectLogger;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;


public class GraphEditorController
{
	@InjectLogger
	private Logger log;
	
	
	@Inject
	private Injector injector;


	@Inject
	@Named("editor")
	private ResourceBundle resources;
	
	
	private ObjectProperty<ProjectContext> currentContext = new SimpleObjectProperty<ProjectContext>(this, "currentContext");


	@FXML
	private Node view;


	@FXML
	private TabPane projectsTabPane;


	@FXML
	private ToolBar projectToolBar;


	@FXML
	private TableView<Property<String>> projectTableView;


	@FXML
	private WebView welcomeWebView;


	@FXML
	public void newProject()
	{
		log.debug("New project clicked");

		ProjectContext context = injector.getInstance(ProjectContext.class);
		
		projectsTabPane.getTabs().add(context.getTab());
	}


	@FXML
	public void exit()
	{
		System.exit(0);
	}


	@FXML
	public void showAbout(ActionEvent event)
	{
		Stage stage = injector.getInstance(Key.get(Stage.class, Names.named("about")));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(view.getScene().getWindow());
		stage.show();
	}
	
	
	public GraphEditorController()
	{
	}


	public void bindProject(GraphProject project)
	{
		if (project != null)
		{
			projectTableView.setUserData(project);
			projectTableView.setItems(project.getProperties());
		}
		else
		{
			projectTableView.setUserData(null);
			projectTableView.setItems(null);
		}

		projectToolBar.setVisible(!projectsTabPane.getTabs().isEmpty());
	}


	public void initialize()
	{
		bindProject(null);

		projectsTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue)
			{
				if (newValue != null)
				{
					Object data = newValue.getUserData();

					if (data instanceof ProjectContext)
					{
						ProjectContext context = (ProjectContext) data;

						// log.debug("Project selected: " + project.getName());

						bindProject(context.getProject());
						currentContext.set(context);
						
						return;
					}

				}

				bindProject(null);
			}
		});

		welcomeWebView.getEngine().load("http://graph-editor.xesenix.pl");

		@SuppressWarnings("unchecked")
		TableColumn<Property<String>, String> propertyNameColumn = (TableColumn<Property<String>, String>) projectTableView
			.getColumns().get(0);
		propertyNameColumn.setCellValueFactory(new SimplePropertyNameValueFactory());

		@SuppressWarnings("unchecked")
		TableColumn<Property<String>, String> propertyValueColumn = (TableColumn<Property<String>, String>) projectTableView
			.getColumns().get(1);

		propertyValueColumn.setCellValueFactory(new SimplePropertyValueFactory());
		propertyValueColumn.setCellFactory(new TextBoxCellFactory());
	}


	public Node getView()
	{
		return view;
	}
}
