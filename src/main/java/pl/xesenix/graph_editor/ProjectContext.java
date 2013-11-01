
package pl.xesenix.graph_editor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tab;

import com.google.inject.Inject;
import com.google.inject.name.Named;


public class ProjectContext
{
	private ObjectProperty<GraphProject> project = new SimpleObjectProperty<GraphProject>(this, "project");


	@Inject
	private GraphControl graphControl;


	@Inject
	public ProjectContext(GraphProject project)
	{
		setProject(project);
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


	public GraphControl getGraphControl()
	{
		return this.graphControl;
	}
}
