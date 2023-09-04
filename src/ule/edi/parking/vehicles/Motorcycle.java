package ule.edi.parking.vehicles;

import java.util.Objects;

public class Motorcycle implements Vehicle {
	
	private String registration;
	
	public  Motorcycle (String currentRegistration){
		registration=currentRegistration;	
	}

	@Override
	public int hashCode() {
		return Objects.hash("Motorcycle", registration);
	}

	@Override
	public boolean equals(Object obj) {
		
		
		if (this == obj) {
			return true;
		}
		if(obj instanceof Motorcycle) {
			Motorcycle other = (Motorcycle) obj;
				return (this.registration.equals(other.registration));
		}
		return false;
		
		
//		Motorcycle otherObj = (Motorcycle) obj;
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
		return "{\"Tipo\":\"Motocicleta\", \"Matrícula\":\"" + registration + "\"}";
	}

}
