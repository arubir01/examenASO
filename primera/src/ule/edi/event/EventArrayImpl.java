package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;


public class EventArrayImpl implements Event {
	
	private String name;
	private Date eventDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
   public EventArrayImpl(String name, Date date, int nSeats){
	 //TODO 
	 // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en Configuration.java   
	 // Debe crear el array de butacas
	   this.name = name;
	   this.eventDate = date;
	   this.nSeats = nSeats;
	   
	   seats = new Seat[nSeats];
	   
	   this.price = Configuration.DEFAULT_PRICE;
	   this.discountAdvanceSale = Configuration.DEFAULT_DISCOUNT; 
   }
   
   
   public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear el array de butacas
	   this.name = name;
	   this.eventDate = date;
	   this.nSeats = nSeats;
	   
	   seats = new Seat[nSeats];
	   
	   this.price = price;
	   this.discountAdvanceSale = discount; 	  
   }


@Override
public String getName() {
	// TODO Auto-generated method stub
	return this.name;
}


@Override
public Date getDateEvent() {
	// TODO Auto-generated method stub
	return this.eventDate;
}


@Override
public Double getPrice() {
	// TODO Auto-generated method stub
	return this.price;
}


@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return this.discountAdvanceSale;
}


@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	int seats = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null)
			seats ++;
	}
	
	return seats;
}


@Override
public int getNumberOfNormalSaleSeats() {
	// TODO Auto-generated method stub
	int normalSeats = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null && seats[i].getType() == Configuration.Type.NORMAL)
			normalSeats ++;
	}
	
	return normalSeats;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	int advanceSeats = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null && seats[i].getType() == Configuration.Type.ADVANCE_SALE)
			advanceSeats ++;
	}
	
	return advanceSeats;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub
	return nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	int freeSeats = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] == null)
			freeSeats ++;
	}
	
	return freeSeats;
}


@Override
public Seat getSeat(int pos) {
	// TODO Auto-generated method stub
	if(pos < 1 || pos >= nSeats) {
		return null;
	}
	return seats[pos - 1];
}


@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	Person persona = null;
	if(pos < 1 || pos >= nSeats || seats[pos - 1] == null) {
		return persona;
	}
	persona = seats[pos - 1].getHolder();
	seats[pos - 1] = null;
	
	return persona;
}


@Override
public boolean sellSeat(int pos, Person p, boolean advanceSale) {
	// TODO Auto-generated method stub
	if(seats[pos - 1] == null) {
		
		if(advanceSale == true) {
			seats[pos - 1] = new Seat(this, pos, Configuration.Type.ADVANCE_SALE, p);
		} else {
			seats[pos - 1] = new Seat(this, pos, Configuration.Type.NORMAL, p);	
		}
		return true;
	}
	return false;
}


@Override
public int getNumberOfAttendingChildren() {
	// TODO Auto-generated method stub
	int numberChildren = 0;
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null && seats[i].getHolder().getAge() <= Configuration.CHILDREN_EXMAX_AGE)
			numberChildren ++;
	}
	
	return numberChildren;
}


@Override
public int getNumberOfAttendingAdults() {
	// TODO Auto-generated method stub
	int numberAdults = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null
		&& seats[i].getHolder().getAge() > Configuration.CHILDREN_EXMAX_AGE
		&& seats[i].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)
			numberAdults ++;
	}
	
	return numberAdults;
}


@Override
public int getNumberOfAttendingElderlyPeople() {
	// TODO Auto-generated method stub
	int numberElder = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null && seats[i].getHolder().getAge() >= Configuration.ELDERLY_PERSON_INMIN_AGE)
			numberElder ++;
	}
	
	return numberElder;
}


@Override
public List<Integer> getAvailableSeatsList() {
	// TODO Auto-generated method stub
	
	List<Integer> free = new ArrayList<Integer>();
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null)
			free.add(i + 1);
	}
	
	return free;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> advancedSaleSeats = new ArrayList<Integer>();
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null && seats[i].getType() == Configuration.Type.ADVANCE_SALE)
			advancedSaleSeats.add(i + 1);
	}
	
	return advancedSaleSeats;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	// TODO Auto-generated method stub
	int aux = 0;
	int consecutivos = 0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] == null) {
			aux ++;
		} else {
			
			if(consecutivos < aux) {
				consecutivos = aux;
				aux = 0;
			}
		}
	}
	
	if(consecutivos < aux) {
		consecutivos = aux;
	}
	
	return consecutivos;
}


@Override
public Double getPrice(Seat seat) {
	// TODO Auto-generated method stub
	Double prize = 0.0;
	
	if(this == seat.getEvent()) {
		
		if(seat.getType() == Configuration.Type.ADVANCE_SALE) {
			prize = prize - price * (discountAdvanceSale / 100.0);
			
		} else {
			prize = price;
		}
	}
	
	return prize;
}


@Override
public Double getCollectionEvent() {
	// TODO Auto-generated method stub
	Double collection = 0.0;
	
	for(int i = 0; i < nSeats; i++) {
		if(seats[i] != null)
			collection += this.getPrice(seats[i]);
	}
	
	return collection;
}


@Override
public int getPosPerson(Person p) {
	// TODO Auto-generated method stub
	int pos = -1;
	int i = 0;
	
	while(i < nSeats && pos == -1) {
		if(seats[i] != null && seats[i].getHolder().equals(p) == true)
			pos = i + 1;
		
		i++;
	}
	
	return pos;
}


@Override
public boolean isAdvanceSale(Person p) {
	// TODO Auto-generated method stub
	
	boolean sale = false;
	int i = 0;
	
	while(i < nSeats && sale == false) {
		if(seats[i] != null
		&& seats[i].getHolder().equals(p) == true
		&& seats[i].getType() == Configuration.Type.ADVANCE_SALE)
			sale = true;
		
		i++;
	}
	
	return venta;
}
   


}	
