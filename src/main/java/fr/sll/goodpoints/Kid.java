package fr.sll.goodpoints;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kids")
public class Kid implements Serializable {

	private static final long serialVersionUID = -5998566372580851293L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "score", nullable = false)
	private Integer score;

	@Column(name = "goal", nullable = false)
	private Integer goal;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<History> history;

	public Kid() {
		super();
		this.setHistory(new HashSet<>());	}

	public Kid(final String name, final Integer goal) {
		this.name = name;
		this.goal = goal;
		this.score = 0;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final Integer getScore() {
		return score;
	}

	public final void setScore(final Integer score) {
		this.score = score;
	}

	public final Integer getGoal() {
		return goal;
	}

	public final void setGoal(final Integer goal) {
		this.goal = goal;
	}

	public final void addPoints(final Integer points, final String reason) {
		this.score += points;
		this.history.add(new History(this, points, reason));
	}

	@Override
	public String toString() {
		return "Kid [id=" + id + ", name=" + name + ", score=" + score + ", goal=" + goal + "]";
	}

	public Set<History> getHistory() {
		if (this.history == null) {
			return new HashSet<>();
		}
		return history;
	}

	public void setHistory(final Set<History> history) {
		this.history = history;
	}

}
