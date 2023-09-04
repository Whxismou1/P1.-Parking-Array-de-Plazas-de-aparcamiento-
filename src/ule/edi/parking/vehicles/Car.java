package ule.edi.parking.vehicles;

import java.util.Objects;


public class Car implements Vehicle {
	
	private String registration;

	public Car(String currentRegistration) {
		registration = currentRegistration;
	}

	@Override
	public int hashCode() {
		return Objects.hash("Car", registration);
	}

	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) {
			return true;
		}
		if(obj instanceof Car) {
			Car other = (Car) obj;
				return (this.registration.equals(other.registration));
			
		}
		return false;
		
//		Car otherObj = (Car) obj;
//		if(this.getClass().equals(obj.getClass()) && this.getRegistration().equals(otherObj.getRegistration())) {
//			return true;
//		} else {
//			return false;
//		}
		
	}	
	
	@Override
	public String getRegistration() {
		return registration;
	}

	@Override
	public String toString() {
		return "{\"Tipo\":\"Car\", \"Matrícula\":\"" + registration + "\"}";
	}

}
