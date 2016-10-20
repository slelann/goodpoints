package fr.sll.goodpoints;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class History {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "kidId", nullable = false)
	private Kid kid;

	@Column(name = "timestamp", nullable = false)
	private Long timestamp;

	@Column(name = "points", nullable = false)
	private Integer points;

	@Column(name = "goal", nullable = true)
	private String reason;

	public History() {
		super();
	}

	public History(final Kid kid, final Integer points, final String reason) {
		super();
		this.kid = kid;
		this.points = points;
		this.reason = reason;
		this.timestamp = Instant.now().getEpochSecond();
	}


	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final Kid getKid() {
		return kid;
	}

	public final void setKid(final Kid kid) {
		this.kid = kid;
	}

	public final Long getTimestamp() {
		return timestamp;
	}

	public final void setTimestamp(final Long timestamp) {
		this.timestamp = timestamp;
	}

	public final Integer getPoints() {
		return points;
	}

	public final void setPoints(final Integer points) {
		this.points = points;
	}

	public final String getReason() {
		return reason;
	}

	public final void setReason(final String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", kid=" + kid + ", timestamp=" + timestamp + ", points=" + points + ", reason=" + reason + "]";
	}


}
