package pl.xesenix.scene.control.cell;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class TextBoxCellFactory implements Callback<TableColumn<Property<String>, String>, TableCell<Property<String>, String>>
{
	@Override
	public TableCell<Property<String>, String> call(TableColumn<Property<String>, String> param)
	{

		TableCell<Property<String>, String> cell = new TableCell<Property<String>, String>() {
			TextField valueField;


			public void startEdit()
			{
				super.startEdit();

				if (valueField == null)
				{
					valueField = new TextField();

					valueField.focusedProperty().addListener(new ChangeListener<Boolean>() {
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
						{
							if (!newValue && valueField != null)
							{
								commitEdit(valueField.getText());
							}
						}
					});

				}

				valueField.textProperty().set(getText());
				valueField.selectAll();

				setGraphic(valueField);
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			}


			@Override
			public void cancelEdit()
			{
				setGraphic(null);

				super.cancelEdit();
				setContentDisplay(ContentDisplay.TEXT_ONLY);
			}


			@Override
			protected void updateItem(String item, boolean empty)
			{
				super.updateItem(item, empty);

				if (!empty)
				{
					setText(item);
				}
				else
				{
					setText(null);
				}
			}

		};

		return cell;
	}
}