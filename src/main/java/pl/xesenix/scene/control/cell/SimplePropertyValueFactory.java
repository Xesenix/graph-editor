package pl.xesenix.scene.control.cell;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public final class SimplePropertyValueFactory implements Callback<TableColumn.CellDataFeatures<Property<String>, String>, ObservableValue<String>>
{
	@Override
	public ObservableValue<String> call(CellDataFeatures<Property<String>, String> arg0)
	{
		return arg0.getValue();
	}
}