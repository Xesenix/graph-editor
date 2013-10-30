
package pl.xesenix.graph_editor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tab;

import com.google.inject.Inject;


public class ProjectContext
{
	private ObjectProperty<GraphProject> project = new SimpleObjectProperty<GraphProject>(this, "project");


	private Tab tab;


	@Inject
	public ProjectContext(GraphProject project)
	{
		setProject(project);
		
		tab = new Tab();
		tab.textProperty().bind(getProject().nameProperty());
		tab.setUserData(this);
	}


	public GraphProject getProject()
	{
		return this.project.get();
	}


	public void setProject(GraphProject value)
	{
		project.set(value);
	}


	public ObjectProperty<GraphProject> getProjectProperty()
	{
		return project;
	}


	public Tab getTab()
	{
		return this.tab;
	}
}
