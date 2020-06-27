package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 110);

	}
	
	@Test
	public void eventoCompleto() throws Exception {
		Double num = 200.0;
		Byte num2 = 10;
		e = new EventArrayImpl("ELE", parseLocalDate("24/02/2018 17:00:00"), 20, num, num2);
	}
	
	@Test
	public void testEventoVacio() throws Exception {
		
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==110);
	    Assert.assertEquals(e.getNumberOfAvailableSeats(), 110);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
			
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34),false));	//venta normal
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);  
	    Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	  
	}
	

	
	@Test
	public void testgetCollection() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), true),true);
		Assert.assertTrue(ep.getCollectionEvent()==75);					
	}
	
	@Test
	public void getName() throws Exception{
	    Assert.assertTrue(e.getName().equals("The Fabulous Five"));				
	}
	
	@Test
	public void getDate() throws Exception{
	    Assert.assertTrue(e.getDateEvent().equals(parseLocalDate("24/02/2018 17:00:00")));				
	}
	
	@Test
	public void getPrice() throws Exception{
	    Assert.assertTrue(e.getPrice() == Configuration.DEFAULT_PRICE);				
	}
	
	@Test
	public void getDiscount() throws Exception{
	    Assert.assertTrue(e.getDiscountAdvanceSale() == Configuration.DEFAULT_DISCOUNT);				
	}
	
	@Test
	public void getSoldSeats() throws Exception{
	    Assert.assertTrue(e.getNumberOfSoldSeats() == 0);
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34),false));	
	    Assert.assertTrue(e.getNumberOfSoldSeats() == 1);			
	}
	
	@Test
	public void getNormalSoldSeats() throws Exception{
	    Assert.assertTrue(e.getNumberOfNormalSaleSeats() == 0);
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34), true));	
	    Assert.assertTrue(e.getNumberOfNormalSaleSeats() == 0);	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040C","Pepe", 35), false));	
	    Assert.assertTrue(e.getNumberOfNormalSaleSeats() == 1);			
	}
	
	@Test
	public void getAdvanceSoldSeats() throws Exception{
	    Assert.assertTrue(e.getNumberOfAdvanceSaleSeats() == 0);
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34), false));	
	    Assert.assertTrue(e.getNumberOfAdvanceSaleSeats() == 0);	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040C","Pepe", 35), true));	
	    Assert.assertTrue(e.getNumberOfAdvanceSaleSeats() == 1);			
	}
	
	@Test
	public void getNumberOfSeats() throws Exception{
	    Assert.assertTrue(e.getNumberOfSeats() == 110);		
	}
	
	@Test
	public void getNumberOfAvailableSeats() throws Exception{
	    Assert.assertTrue(e.getNumberOfAvailableSeats() == 110);	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34), false));	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040C","Pepe", 35), true));	
	    Assert.assertTrue(e.getNumberOfAvailableSeats() == 108);		
	}
	
	@Test
	public void getBadSeat() throws Exception{	
	    Assert.assertTrue(e.getSeat(-5) == null);	
	    Assert.assertTrue(e.getSeat(e.getNumberOfSeats() + 5) == null);		
	}
	
	@Test
	public void getGoodSeat() throws Exception{	
	    Assert.assertTrue(e.getSeat(1) == null);	
	}
	
	@Test
	public void refoundBadSeat() throws Exception{	
	    Assert.assertTrue(e.refundSeat(1) == null);	
	    Assert.assertTrue(e.refundSeat(-5) == null);	
	    Assert.assertTrue(e.refundSeat(e.getNumberOfSeats() + 5) == null);	
	}
	
	@Test
	public void refoundGoodSeat() throws Exception{	
		Person persona = new Person("10203040A","Alice", 34);
	    Assert.assertTrue(e.sellSeat(1, persona, false));	
	    Assert.assertTrue(e.refundSeat(1).equals(persona));	
	}

	@Test
	public void sellBadSeat() throws Exception{	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34), false));	
	    Assert.assertFalse(e.sellSeat(1, new Person("10203040C","Pepe", 35), true));	
	}

	@Test
	public void getNumberOfAttendingChildren() throws Exception{	
	    Assert.assertTrue(e.getNumberOfAttendingChildren() == 0);	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040B","Luis", 45), false));
	    Assert.assertTrue(e.getNumberOfAttendingChildren() == 0);	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(e.sellSeat(3, new Person("10203040Z","Pepe", 9), false));	
	    Assert.assertTrue(e.getNumberOfAttendingChildren() == 2);	
	}

	@Test
	public void getNumberOfAttendingAdults() throws Exception{	
	    Assert.assertTrue(e.getNumberOfAttendingAdults() == 0);	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040B","Luis", 80), false));
	    Assert.assertTrue(e.getNumberOfAttendingAdults() == 0);	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(e.getNumberOfAttendingAdults() == 0);	
	    Assert.assertTrue(e.sellSeat(3, new Person("10203040Z","Pepe", 25), false));	
	    Assert.assertTrue(e.getNumberOfAttendingAdults() == 1);		
	}

	@Test
	public void getNumberOfAttendingElderlyPeople() throws Exception{	
	    Assert.assertTrue(e.getNumberOfAttendingElderlyPeople() == 0);	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(e.getNumberOfAttendingElderlyPeople() == 0);	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040B","Luis", 80), false));
	    Assert.assertTrue(e.getNumberOfAttendingElderlyPeople() == 1);		
	}

	@Test
	public void getAvailableSeatsList() throws Exception{	
	    Assert.assertTrue(e.getAvailableSeatsList().isEmpty());	
	    
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040B","Luis", 80), false));
	    
	    Assert.assertTrue(e.getAvailableSeatsList().size() == 2);			
	}

	@Test
	public void getAdvanceSaleSeatsList() throws Exception{	
	    Assert.assertTrue(e.getAdvanceSaleSeatsList().isEmpty());	
	    
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(e.getAdvanceSaleSeatsList().size() == 0);	
	    
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040B","Luis", 80), true));
	    
	    Assert.assertTrue(e.getAdvanceSaleSeatsList().size() == 1);			
	}

	@Test
	public void getMaxNumberConsecutiveSeats() throws Exception{	
		EventArrayImpl prueba = new EventArrayImpl("Eñe", parseLocalDate("24/02/2018 17:00:00"), 10);
	    Assert.assertTrue(prueba.getMaxNumberConsecutiveSeats() == 10);	
	    Assert.assertTrue(prueba.sellSeat(1, new Person("10203040A","Alice", 10), false));	
	    Assert.assertTrue(prueba.sellSeat(2, new Person("10203040B","Luis", 80), false));
	    Assert.assertTrue(prueba.getMaxNumberConsecutiveSeats() == 8);		
	    Assert.assertTrue(prueba.sellSeat(7, new Person("10203040B","Luis", 80), false));
	    Assert.assertTrue(prueba.getMaxNumberConsecutiveSeats() == 4);    
	}

	@Test
	public void getPriceBadEvent() throws Exception{	
		EventArrayImpl prueba = new EventArrayImpl("Eñe", parseLocalDate("24/02/2018 17:00:00"), 20);
	    Seat asiento = new Seat(prueba, 1, Configuration.Type.NORMAL, new Person("10203040A","Alice", 10));
	    Assert.assertTrue(e.getPrice(asiento) == 0.0);		
	}
	
	@Test
	public void getPriceGoodEvent() throws Exception{	
	    Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 10), false));
	    Assert.assertTrue(e.getPrice(e.getSeat(1)) == e.getPrice());		
	}
	
	@Test
	public void getPosPerson() throws Exception{	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040B","Luis", 80), false));
		Person persona = new Person("10203040A","Alice", 10);
	    Assert.assertTrue(e.getPosPerson(persona) == -1);	
	    Assert.assertTrue(e.sellSeat(10, persona, false));
	    Assert.assertTrue(e.getPosPerson(persona) == 10);		
	}
	
	@Test
	public void isAdvanceSale() throws Exception{	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040B","Luis", 80), false));
		Person persona = new Person("10203040A","Alice", 10);
	    Assert.assertFalse(e.isAdvanceSale(persona));	
	    Assert.assertTrue(e.sellSeat(10, persona, true));
	    Assert.assertTrue(e.isAdvanceSale(persona));	
		Person persona2 = new Person("10203040Z","Luis", 10);
	    Assert.assertTrue(e.sellSeat(5, persona2, false));
	    Assert.assertFalse(e.isAdvanceSale(persona2));		
	}
	
	@Test
	public void seatToString() throws Exception{	
	    Assert.assertTrue(e.sellSeat(2, new Person("10203040B","Luis", 80), false)); 
	    Assert.assertTrue(e.getSeat(2).toString().equals("{'Event':'The Fabulous Five', 'Holder':{ NIF: Luis  Name : 10203040B, Age:80}, 'Price':100.0}"));
	    e.getSeat(2).setType(Configuration.Type.ADVANCE_SALE); 
	    Assert.assertTrue(e.getSeat(2).toString().equals("{'Event':'The Fabulous Five', 'Holder':{ NIF: Luis  Name : 10203040B, Age:80}, 'Price':75.0}"));
	   
	}
	
	@Test
	public void personNameTest() throws Exception{	
		Person persona = new Person("Luis", "10203040B", 80);
		Assert.assertTrue(persona.getName().equals("Luis"));
		persona.setName("Fede");
		Assert.assertTrue(persona.getName().equals("Fede"));
	}
	
	@Test
	public void personNifTest() throws Exception{	
		Person persona = new Person("Luis", "10203040B", 80);
		Assert.assertTrue(persona.getNif().equals("10203040B"));
		persona.setNif("71035242H");
		Assert.assertTrue(persona.getNif().equals("71035242H"));
	}
	
	@Test
	public void personAgeTest() throws Exception{	
		Person persona = new Person("Luis", "10203040B", 80);
		Assert.assertTrue(persona.getAge() == 80);
		persona.setAge(26);
		Assert.assertTrue(persona.getAge() == 26);
	}
	
	// TODO EL RESTO DE MÉTODOS DE TESTS NECESARIOS PARA LA COMPLETA COMPROBACIÓN DEL BUEN FUNCIONAMIENTO DE TODO EL CÓDIGO IMPLEMENTADO
}
