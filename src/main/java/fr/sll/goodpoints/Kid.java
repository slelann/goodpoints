package fr.sll.goodpoints;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	public Kid() {
		super();
	}

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

	@Override
	public String toString() {
		return "Kid [id=" + id + ", name=" + name + ", score=" + score + ", goal=" + goal + "]";
	}

}
