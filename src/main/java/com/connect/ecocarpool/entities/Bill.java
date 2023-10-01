package com.connect.ecocarpool.entities;

public class Bill {

	private int id;
	private String date;
	private String pickup;
	private String dropoff;
	private int distance;
	private String serviceType;
	private int payment;
	private String driver;

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(int id, String date, String pickup, String dropoff, int distance, String serviceType, String driver) {
		super();
		this.id = id;
		this.date = date;
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.distance = distance;
		this.serviceType = serviceType;
		this.driver = driver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getDropoff() {
		return dropoff;
	}

	public void setDropoff(String dropoff) {
		this.dropoff = dropoff;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getPayment() {
		this.payment = this.payment(this.getServiceType());
		return this.payment;
	}
	
	

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	private int payment(String serviceType) {

		switch (serviceType) {
		case "Go Saver": {

			return (30 * this.getDistance());

		}
		case "Go Min": {
			return (40 * this.getDistance());
		}
		case "Go": {
			return (50 * this.getDistance());
		}
		case "Go Premium": {
			return (60 * this.getDistance());
		}
		case "Bike": {
			return (10 * this.getDistance());
		}
		case "Riksha": {
			return (20 * this.getDistance());
		}
		default: {
			return 0;
		}
		}
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", date=" + date + ", pickup=" + pickup + ", dropoff=" + dropoff + ", distance="
				+ distance + ", serviceType=" + serviceType + ", payment=" + payment + "]";
	}

}
