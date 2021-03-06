
package pl.xesenix.graph_editor;

import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GraphProject
{
	private StringProperty name = new SimpleStringProperty(this, "name", "");


	private ObservableList<Property<String>> properties;

	
	@Inject
	public GraphProject(@Named("project") ResourceBundle resources)
	{
		setName(resources.getString("default_name"));
	}
	

	public final StringProperty nameProperty()
	{
		return name;
	}


	public final String getName()
	{
		return name.get();
	}


	public void setName(String value)
	{
		name.set(value);
	}


	public ObservableList<Property<String>> getProperties()
	{
		if (properties == null)
		{
			properties = FXCollections.observableArrayList();
	
			properties.add(this.name);
		}

		return properties;
	}
}
