package gapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean New;

	private boolean PendingReview;

	private boolean Denied;

	private boolean RecommendAdmit;

	private boolean Condition;

	private String name;

	public Status() {
		
		this.name = "Not Submitted";
		this.New = false;
		this.PendingReview = false;
		this.Denied = false;
		this.RecommendAdmit = false;
		this.Condition = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPendingReview() {
		return PendingReview;
	}

	public void setPendingReview(boolean pendingReview) {
		PendingReview = pendingReview;
	}

	public boolean isDenied() {
		return Denied;
	}

	public void setDenied(boolean denied) {
		Denied = denied;
	}

	public boolean isRecommendAdmit() {
		return RecommendAdmit;
	}

	public void setRecommendAdmit(boolean recommendAdmit) {
		RecommendAdmit = recommendAdmit;
	}

	public boolean isCondition() {
		return Condition;
	}

	public void setCondition(boolean condition) {
		Condition = condition;
	}

	public boolean isNew() {
		return New;
	}

	public void setNew(boolean new1) {
		New = new1;
	}

}
