package fr.sll.goodpoints;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class History {

	@Id
	@GeneratedValue
	private Long pointId;

	private Long kidId;

	private Long timestamp;
	private Long points;
	private String reason;

	public History(final Long kidId, final Long points, final String reason) {
		super();
		this.kidId = kidId;
		this.points = points;
		this.reason = reason;
		this.timestamp = Instant.now().getEpochSecond();
	}


	public final Long getPointId() {
		return pointId;
	}

	public final void setPointId(final Long pointId) {
		this.pointId = pointId;
	}

	public final Long getKidId() {
		return kidId;
	}

	public final void setKidId(final Long kidId) {
		this.kidId = kidId;
	}

	public final Long getTimestamp() {
		return timestamp;
	}

	public final void setTimestamp(final Long timestamp) {
		this.timestamp = timestamp;
	}

	public final Long getPoints() {
		return points;
	}

	public final void setPoints(final Long points) {
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
		return "Point [pointId=" + pointId + ", personId=" + kidId + ", timestamp=" + timestamp + ", points=" + points + ", reason=" + reason + "]";
	}


}
