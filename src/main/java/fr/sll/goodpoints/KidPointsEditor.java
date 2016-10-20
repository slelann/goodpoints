package fr.sll.goodpoints;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form in
 * multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Virin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class KidPointsEditor extends VerticalLayout {

	private static final long serialVersionUID = 2142247363891643290L;

	private static final Object[] itemIds = {"1", "3", "5", "10"};

	private final KidRepository repository;

	/**
	 * The currently edited kid
	 */
	private Kid kid;

	/* Fields to edit properties in Kid entity */
	TextField score = new TextField("Score");
	ComboBox comboPoints = new ComboBox("Points");

	/* Action buttons */
	Button savePlus = new Button("Plus", FontAwesome.PLUS);
	Button saveMinus = new Button("Moins", FontAwesome.MINUS);
	Button cancel = new Button("Cancel");
	CssLayout actions = new CssLayout(savePlus, saveMinus, cancel);

	@Autowired
	public KidPointsEditor(final KidRepository repository) {
		this.repository = repository;

		addComponents(comboPoints, actions);

		comboPoints.addItems(itemIds);
		comboPoints.setValue(itemIds[0]);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		savePlus.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
		savePlus.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		saveMinus.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
		saveMinus.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		savePlus.addClickListener(e -> addPointsToKid(kid, Integer.parseInt((String) comboPoints.getValue())));
		saveMinus.addClickListener(e -> subtractPointsToKid(kid, Integer.parseInt((String) comboPoints.getValue())));
		cancel.addClickListener(e -> editKid(kid));
		setVisible(false);
	}


	public interface ChangeHandler {

		void onChange();
	}

	public final void editKid(final Kid c) {
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			kid = repository.findOne(c.getId());
		}
		else {
			kid = c;
		}
		cancel.setVisible(persisted);

		// Bind kid properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving

		BeanFieldGroup.bindFieldsUnbuffered(kid, this);

		setVisible(true);

		// A hack to ensure the whole form is visible
		savePlus.focus();
	}


	private void subtractPointsToKid(final Kid kid, final int points) {
		Integer newScore = Math.max(0, kid.getScore() - points);
		kid.setScore(newScore);
		repository.save(kid);
	}

	private void addPointsToKid(final Kid kid, final int points) {
		Integer newScore = kid.getScore() + points;
		//kid.setScore(newScore);
		kid.addPoints(points, "test");
		repository.save(kid);
	}

	public void setChangeHandler(final ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		savePlus.addClickListener(e -> h.onChange());
		saveMinus.addClickListener(e -> h.onChange());
	}

}
