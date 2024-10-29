package es.uma.itis.iis;

public class Car {

	private int make;
	private int model;
	private int year;
	private int mileage;
	private int pricePerDay;

	public void getMake() {
		// TODO - implement Class.getMake
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param make
	 */
	public void setMake(int make) {
		this.make = make;
	}

	public void getModel() {
		// TODO - implement Class.getModel
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(int model) {
		this.model = model;
	}

	public void getYear() {
		// TODO - implement Class.getYear
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	public void getMileage() {
		// TODO - implement Class.getMileage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mileage
	 */
	public void setMileage(int mileage) {
		if (mileage < this.mileage) {
			throw new RuntimeException("Not allowed.");
		}
		this.mileage = mileage;
	}

	public void getPricePerDay() {
		// TODO - implement Class.getPricePerDay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pricePerDay
	 */
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

}