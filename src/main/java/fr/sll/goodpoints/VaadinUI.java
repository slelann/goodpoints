package fr.sll.goodpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private static final long serialVersionUID = 1211547877716713105L;

	private final KidRepository repo;

	private final KidEditor kidEditor;
	private final KidPointsEditor pointsEditor;

	final Grid grid;

	private final Button addNewBtn;

	@Autowired
	public VaadinUI(final KidRepository repo, final KidPointsEditor pointsEditor, final KidEditor kidEditor) {
		this.repo = repo;
		this.pointsEditor = pointsEditor;
		this.kidEditor = kidEditor;
		this.grid = new Grid();
		this.addNewBtn = new Button("New Kid", FontAwesome.PLUS);
	}

	@Override
	protected void init(final VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, kidEditor, pointsEditor);
		setContent(mainLayout);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "name", "goal", "score");

		// Hook logic to components
		kidEditor.setVisible(false);
		// Connect selected Kid to pointsEditor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				pointsEditor.setVisible(false);
			}
			else {
				pointsEditor.editKid((Kid) grid.getSelectedRow());
			}
		});

		// Instantiate and edit new Kid the new button is clicked
		addNewBtn.addClickListener(e -> kidEditor.editKid(new Kid("", 30)));

		// Listen changes made by the pointsEditor, refresh data from backend
		pointsEditor.setChangeHandler(() -> {
			pointsEditor.setVisible(false);
			listKids(null);
		});

		// Initialize listing
		listKids(null);
	}

	// tag::listKids[]
	void listKids(final String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(
					new BeanItemContainer<>(Kid.class, repo.findAll()));
		}
		else {
			grid.setContainerDataSource(new BeanItemContainer<>(Kid.class,
					repo.findByNameStartsWithIgnoreCase(text)));
		}
	}
	// end::listKids[]

}
