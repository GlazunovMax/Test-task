package by.testTask.testApp.domain;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messageApp")
public class MessageApp {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;//Long

	@Column
    private String request;
	@Column
    private int bid;
	@Column
    private LocalDate date;
	@Column
    private Status status;
    
    
   

	public MessageApp(){}
    
	public MessageApp(String request, int bid, Status status) {
		super();
		this.request = request;
		this.bid = bid;
		this.status = status;
		//this.status = Status.PROCESSING;
		this.date = LocalDate.now();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	 public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}
    
    
}
