
package pl.xesenix.graph_editor;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GraphProject
{
	private StringProperty name = new SimpleStringProperty(this, "name", "");


	private ObservableList<Property<String>> properties;


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
