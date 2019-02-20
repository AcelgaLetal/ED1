package ule.edi.event;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10, 100);

	}
	
	@Test
	public void testSomething() throws Exception {
		
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==110);
	    Assert.assertEquals(e.getNumberOfSilverSeats(), 110);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
			
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		e.sellSeat(1, new Person("10203040A","Alice", 34),Type.GOLD);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
	  
	}
	
	@Test
	public void testgetAvailableSilverSeatsListBasic() throws Exception{
		 Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.SILVER),true);
		Assert.assertEquals(ep.getAvailableSilverSeatsList().toString(), "[2]");					
	}
	
	@Test
	public void testGetName() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 2, 2);
		Assert.assertEquals("Testing", ep.getName());
	}
	
	@Test
	public void testGetDate() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 2, 2);
		Assert.assertEquals(parseLocalDate("17/02/2019 11:07:00"), ep.getDate());
	}
	
	@Test
	public void testGetNumnberOfAttendignChildren() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 4, 4);
		Person personTry = new Person("Alex", "73489228D", 12);
		Person personTry2 = new Person("Olvia", "73489228D",12);
		Person personTry3 = new Person("Alfredo", "73489228D",45);
		
		Assert.assertEquals(0, ep.getNumberOfAttendingChildren());
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);		
		Assert.assertEquals(1, ep.getNumberOfAttendingChildren());
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		Assert.assertEquals(1, ep.getNumberOfAttendingChildren());
		ep.sellSeat(2, personTry2, Configuration.Type.SILVER);
		Assert.assertEquals(2, ep.getNumberOfAttendingChildren());
		
	}
	
	@Test
	public void testGetNumnberOfAttendignAdults() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 4, 4);
		Person personTry = new Person("Alex", "73489228D", 25);
		Person personTry2 = new Person("Olvia", "73489228D", 45);
		Person personTry3 = new Person("Alfredo", "73484528D",13);
		Person personTry4 = new Person("Jaime", "34489228D",80);
		Person personTry5 = new Person("Aurelio", "73209228D",80);
		Person personTry6 = new Person("Mario", "734846628D",12);
		
		Assert.assertEquals(0, ep.getNumberOfAttendingAdults());
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);		
		Assert.assertEquals(1, ep.getNumberOfAttendingAdults());
		ep.sellSeat(2, personTry4, Configuration.Type.GOLD);
		Assert.assertEquals(1, ep.getNumberOfAttendingAdults());
		ep.sellSeat(3, personTry6, Configuration.Type.GOLD);
		Assert.assertEquals(1, ep.getNumberOfAttendingAdults());
		
		
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		Assert.assertEquals(1, ep.getNumberOfAttendingAdults());
		ep.sellSeat(2, personTry2, Configuration.Type.SILVER);
		Assert.assertEquals(2, ep.getNumberOfAttendingAdults());
		ep.sellSeat(3, personTry5, Configuration.Type.SILVER);
		Assert.assertEquals(2, ep.getNumberOfAttendingAdults());
	}
	
	@Test
	public void testGetNumnberOfAttendignElderlyPeople() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 4, 4);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Alfredo", "73489228D",40);
		Person personTry3 = new Person("Aurelio", "73209228D",80);
		Person personTry4 = new Person("Mario", "734846628D",12);
		
		Assert.assertEquals(0, ep.getNumberOfAttendingElderlyPeople());
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);		
		Assert.assertEquals(1, ep.getNumberOfAttendingElderlyPeople());	
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		Assert.assertEquals(1, ep.getNumberOfAttendingElderlyPeople());
		
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		Assert.assertEquals(2, ep.getNumberOfAttendingElderlyPeople());
		ep.sellSeat(2, personTry4, Configuration.Type.SILVER);
		Assert.assertEquals(2, ep.getNumberOfAttendingElderlyPeople());
		
		
	}
	
	@Test
	public void testNumberOfSoldSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 6, 6);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Person personTry3 = new Person("Javi", "73489228D", 13);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		
		Assert.assertEquals(3, ep.getNumberOfSoldSeats());
	}
	
	@Test
	public void testNumberOfSoldGoldSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 6, 6);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Person personTry3 = new Person("Javi", "73489228D", 13);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		
		Assert.assertEquals(2, ep.getNumberOfSoldGoldSeats());
	}
	
	@Test
	public void testNumberOfSoldSilverSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 6, 6);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Person personTry3 = new Person("Javi", "73489228D", 13);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		
		Assert.assertEquals(1, ep.getNumberOfSoldSilverSeats());
	}
	
	@Test
	public void testgetNumberOfSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 6, 6);
		
		Assert.assertEquals(12, ep.getNumberOfSeats());
	}
	
	@Test
	public void testNumberOfGoldSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		
		Assert.assertEquals(3, ep.getNumberOfGoldSeats());
	}
	
	@Test
	public void testNumberOfSilverSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		
		Assert.assertEquals(6, ep.getNumberOfSilverSeats());
	}
	
	@Test
	public void testGetNumberOfAvailableSeats() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Person personTry3 = new Person("Javi", "73489228D", 13);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		
		Assert.assertEquals(6, ep.getNumberOfAvailableSeats());
	}
	
	@Test
	public void testSellSeat() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		
		Assert.assertEquals(true, ep.sellSeat(1, personTry, Configuration.Type.GOLD));
		Assert.assertEquals(false, ep.sellSeat(1, personTry2, Configuration.Type.GOLD));
		
		Person personTry3 = new Person("Aurelio", "73209228D",80);
		Person personTry4 = new Person("Mario", "734846628D",12);
		
		Assert.assertEquals(true, ep.sellSeat(1, personTry3, Configuration.Type.SILVER));
		Assert.assertEquals(false, ep.sellSeat(1, personTry4, Configuration.Type.SILVER));
		
		Person personTry5 = new Person("Julio", "73208228D",80);
		Person personTry6 = new Person("Mariola", "7348299628D",12);
		
		Assert.assertEquals(false, ep.sellSeat(0, personTry5, Configuration.Type.GOLD));
		Assert.assertEquals(false, ep.sellSeat(5, personTry6, Configuration.Type.GOLD));
		
		Assert.assertEquals(false, ep.sellSeat(0, personTry5, Configuration.Type.SILVER));
		Assert.assertEquals(false, ep.sellSeat(8, personTry6, Configuration.Type.SILVER));
	}
	
	@Test
	public void testGetSeat() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Seat seatTry = new Seat(ep, 1, Configuration.Type.GOLD, personTry);
		Seat seatTry2 = new Seat(ep, 1, Configuration.Type.SILVER, personTry2);
		
		Assert.assertEquals(null, ep.getSeat(0, Configuration.Type.GOLD));
		Assert.assertEquals(null, ep.getSeat(5, Configuration.Type.GOLD));
		
		Assert.assertEquals(null, ep.getSeat(0, Configuration.Type.SILVER));
		Assert.assertEquals(null, ep.getSeat(8, Configuration.Type.SILVER));
		
		Assert.assertEquals(null, ep.getSeat(1, Configuration.Type.GOLD));
		Assert.assertEquals(null, ep.getSeat(1, Configuration.Type.SILVER));
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);	
		ep.sellSeat(1, personTry2, Configuration.Type.SILVER);
		Assert.assertEquals(seatTry, ep.getSeat(1, Configuration.Type.GOLD));
		Assert.assertEquals(seatTry2, ep.getSeat(1, Configuration.Type.SILVER));
	}
	
	@Test
	public void testRefundSeat() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 3, 6);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		Assert.assertEquals(null, ep.refundSeat(1, Configuration.Type.GOLD));
		Assert.assertEquals(null, ep.refundSeat(1, Configuration.Type.SILVER));
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		
		Assert.assertEquals(personTry, ep.refundSeat(1, Configuration.Type.GOLD));
				
		ep.sellSeat(1, personTry2, Configuration.Type.SILVER);
		
		Assert.assertEquals(personTry2, ep.refundSeat(1, Configuration.Type.SILVER));
		
		Assert.assertEquals(null,ep.refundSeat(0, Configuration.Type.GOLD));
		Assert.assertEquals(null,ep.refundSeat(5, Configuration.Type.GOLD));
		
		Assert.assertEquals(null,ep.refundSeat(0, Configuration.Type.SILVER));
		Assert.assertEquals(null,ep.refundSeat(8, Configuration.Type.SILVER));
	}
	
	
	@Test
	public void testGetAvailableGoldSeatsList() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 6);
		
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		
		Assert.assertEquals(list, ep.getAvailableGoldSeatsList());
	}
	
	@Test
	public void testGetAvailableSilverSeatsList() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		ep.sellSeat(1, personTry, Configuration.Type.SILVER);
		ep.sellSeat(2, personTry2, Configuration.Type.SILVER);
		
		Assert.assertEquals(list, ep.getAvailableGoldSeatsList());
	}
	
	@Test
	public void testGetPriceGold() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8, 8.9, 7.5);
		
		Assert.assertEquals(8,9, ep.getPriceGold());
	}
	
	@Test
	public void testGetPriceSilver() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8, 8.9, 7.5);
		
		Assert.assertEquals(7,5, ep.getPriceSilver());
	}
	
	@Test
	public void testGetPrice() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		Person personTry = new Person("Alex", "73489228D", 70);		
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Seat auxSeat = new Seat(ep, 1, Configuration.Type.GOLD, personTry);
		Seat auxSeat2 = new Seat(ep, 1, Configuration.Type.SILVER, personTry2);
		
		Assert.assertEquals(Configuration.DEFAULT_PRICE_GOLD, ep.getPrice(auxSeat));
		Assert.assertEquals(Configuration.DEFAULT_PRICE_SILVER, ep.getPrice(auxSeat2));
		
	}
	
	@Test
	public void testGeCollectionEvent() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		Person personTry3 = new Person("Javi", "73489228D", 13);
		
		ep.sellSeat(1, personTry, Configuration.Type.GOLD);
		ep.sellSeat(2, personTry2, Configuration.Type.GOLD);
		ep.sellSeat(1, personTry3, Configuration.Type.SILVER);
		
		double total = (Configuration.DEFAULT_PRICE_GOLD * 2) + (Configuration.DEFAULT_PRICE_SILVER);
		
		Assert.assertEquals(total, 0, ep.getCollectionEvent());
	}
	
	@Test
	public void testgetPosPersonGold() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		Assert.assertEquals(-1, ep.getPosPersonGold(personTry));
		
		ep.sellSeat(4, personTry, Configuration.Type.GOLD);
		
		Assert.assertEquals(4, ep.getPosPersonGold(personTry));
		
		Assert.assertEquals(-1, ep.getPosPersonGold(personTry2));
	}
	
	@Test
	public void testGetPosPersonSilver() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Olvia", "73489228D",20);
		
		Assert.assertEquals(-1, ep.getPosPersonSilver(personTry));
		
		ep.sellSeat(4, personTry, Configuration.Type.SILVER);
		
		Assert.assertEquals(4, ep.getPosPersonSilver(personTry));
		
		Assert.assertEquals(-1, ep.getPosPersonSilver(personTry2));
	}
	
	@Test
	public void tesIsGold() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Alex", "73489228D", 70);
		
		ep.sellSeat(4, personTry, Configuration.Type.SILVER);
		ep.sellSeat(4, personTry2, Configuration.Type.GOLD);
		
		Assert.assertEquals(false, ep.isGold(personTry));
		Assert.assertEquals(true, ep.isGold(personTry2));
	}
	
	@Test
	public void tesIsSilver() throws Exception
	{
		Event ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8);
		Person personTry = new Person("Alex", "73489228D", 70);
		Person personTry2 = new Person("Alex", "73489228D", 70);
		
		ep.sellSeat(4, personTry, Configuration.Type.SILVER);
		ep.sellSeat(4, personTry2, Configuration.Type.GOLD);
		
		Assert.assertEquals(true, ep.isSilver(personTry));
		Assert.assertEquals(false, ep.isSilver(personTry2));
	}
	
	@Test
	public void testSetPriceGold() throws Exception
	{
		EventArrayImpl ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8, 9.0, 4.0);
		
		ep.setPriceGold(20.0);
	}
	
	@Test
	public void testSetPriceSilver() throws Exception
	{
		EventArrayImpl ep = new EventArrayImpl("Testing", parseLocalDate("17/02/2019 11:07:00"), 8, 8, 9.0, 4.0);
		
		ep.setPriceSilver(20.0);
	}
	
}
