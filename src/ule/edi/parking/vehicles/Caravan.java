package ule.edi.parking.vehicles;

import java.util.Objects;


public class Caravan implements Vehicle {
	
	private String registration;

	public Caravan(String currentRegistration) {
		registration = currentRegistration;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash("Caravan", registration);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if(obj instanceof Caravan) {
			Caravan other = (Caravan) obj;
				return (this.registration.equals(other.registration));
			
		}
		return false;
		
		
//		
//		Caravan otherObj = (Caravan) obj;
//		if(this.getClass().equals(obj.getClass()) && this.getRegistration().equals(otherObj.getRegistration())) {
//			return true;
//		}else {
//			return false;
//		}
	}	

	
	
	@Override
	public String getRegistration() {
		return registration;
	}

	@Override
	public String toString() {
		return "{\"Tipo\":\"Caravana\", \"Matrícula\":\"" + registration + "\"}";
	}

}
