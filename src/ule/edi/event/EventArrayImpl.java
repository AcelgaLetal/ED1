package ule.edi.event;

import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import ule.edi.model.Configuration.Type;
import ule.edi.model.*;

public class EventArrayImpl implements Event {
	
	
	private String name;
	private Date date;
	
	private Double priceGold;    // precio de entradas tipo GOLD
	private Double priceSilver;  // precio de entradas tipo SILVER
	
	private int nGold;    // Nº de butacas de tipo GOLD
	private int nSilver;  // Nº de butacas de tipo SILVER
	
	private Seat[] gold;
	private Seat[] silver;
	
	
	
   public Double getPriceGold() {
		return priceGold;
	}


	public void setPriceGold(Double priceGold) {
		this.priceGold = priceGold;
	}


	public Double getPriceSilver() {
		return priceSilver;
	}


	public void setPriceSilver(Double priceSilver) {
		this.priceSilver = priceSilver;
	}


	public EventArrayImpl(String name, Date date, int nGold, int nSilver){ //Constructor1
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE_GOLD y DEFAULT_PRICE_SILVER definidos en Configuration.java
	   
	   // Debe crear los arrays de butacas gold y silver
				
		this.priceGold = Configuration.DEFAULT_PRICE_GOLD;
		this.priceSilver = Configuration.DEFAULT_PRICE_SILVER;
		this.name = name;
		this.date = date;
		this.nGold = nGold;
		this.nSilver = nSilver;
		
		
		gold = new Seat[this.nGold];
		silver = new Seat[this.nSilver];
	   
	}
   
   
   public EventArrayImpl(String name, Date date, int nGold, int nSilver, Double priceGold, Double priceSilver){
	   //TODO 
	   // Debe crear los arrays de butacas gold y silver
	   
	   this.name = name;
	   this.date = date;
	   this.nGold = nGold;
	   this.nSilver = nSilver;
	   this.priceGold = priceGold;
	   this.priceSilver = priceSilver;
	   
	   gold = new Seat[this.nGold];
	   silver = new Seat[this.nSilver];	   
   }
   

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return this.date;
	}

	
	@Override
	public int getNumberOfAttendingChildren() {
		// TODO Auto-generated method stub
		
		int childCounter = 0;
		
		for(int i = 0; i < nGold; i++)
		{
			if(gold[i] != null)
			{	
				if ((gold[i].getHolder().getAge() <= Configuration.CHILDREN_EXMAX_AGE))
				{
					childCounter++;
				}
			}
		}
				
		for(int i = 0; i < nSilver; i++)
		{
			if(silver[i] != null)
			{
				if ((silver[i].getHolder().getAge() <= Configuration.CHILDREN_EXMAX_AGE))
				{
					childCounter++;
				}
			}
		}
		
		return childCounter;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		// TODO Auto-generated method stub
		
		int adultCounter = 0;
		
		for(int i = 0; i < nGold; i++)
		{
			if(gold[i] != null)
			{
				if (gold[i].getHolder().getAge() > Configuration.CHILDREN_EXMAX_AGE && gold[i].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)
				{
					adultCounter++;
				}
			}
		}
		
		for(int i = 0; i < nSilver; i++)
		{
			if (silver[i] != null)
			{
				if (silver[i].getHolder().getAge() > Configuration.CHILDREN_EXMAX_AGE && silver[i].getHolder().getAge() < Configuration.ELDERLY_PERSON_INMIN_AGE)
				{
					adultCounter++;
				}
			}
		}
		
		return adultCounter;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		// TODO Auto-generated method stub
		
		int elderCounter = 0;
		
		for(int i = 0; i < nGold; i++)
		{
			if(gold[i] != null)
			{
				if (gold[i].getHolder().getAge() >= Configuration.ELDERLY_PERSON_INMIN_AGE)
				{
					elderCounter++;
				}
			}
		}
		
		for(int i = 0; i < nSilver; i++)
		{
			if(silver[i] != null)
			{
				if (silver[i].getHolder().getAge() >= Configuration.ELDERLY_PERSON_INMIN_AGE)
				{
					elderCounter++;
				}
			}
		}
		
		return elderCounter;
	}

	@Override
	public int getNumberOfSoldSeats() {
		// TODO Auto-generated method stub
		int sum = 0;
		
		sum =  getNumberOfAttendingChildren() + getNumberOfAttendingAdults() + getNumberOfAttendingElderlyPeople();
		
		return sum;
	}

	@Override
	public int getNumberOfSoldGoldSeats() {
		// TODO Auto-generated method stub
		
		int goldCounter = 0;
		
		for (int i = 0; i < nGold; i++)
		{
			if(gold[i] != null)
			{
				goldCounter++;
			}
		}
		
		return goldCounter;
	}

	@Override
	public int getNumberOfSoldSilverSeats() {
		// TODO Auto-generated method stub
		
		int contador = 0;
		
		for (int i = 0; i < silver.length; i++)
		{
			if(silver[i] != null)
			{
				contador++;
			}
		}
		
		return contador;
	}

	@Override
	public int getNumberOfSeats() {
		// TODO Auto-generated method stub
		int suma = this.nGold + this.nSilver;
		
		return suma;
	}

	@Override
	public int getNumberOfGoldSeats() {
		// TODO Auto-generated method stub
		return this.nGold;
	}

	@Override
	public int getNumberOfSilverSeats() {
		// TODO Auto-generated method stub
		return this.nSilver;
	}


	@Override
	public int getNumberOfAvailableSeats()
	{
		// TODO Auto-generated method stub
		int avGold = nGold - getNumberOfSoldGoldSeats();
		int avSilver = nSilver - getNumberOfSoldSilverSeats();
		
		int sum = avGold + avSilver;
		
		return sum;		
	}


	@Override
	public Seat getSeat(int pos, Type type)
	{
		// TODO Auto-generated method stub
		
		if(pos < 1)
		{
			return null;
		}
		
		if (type == Configuration.Type.GOLD)
		{
			if (pos > nGold)
			{
				return null;
			}
			else
			{
				if (gold[pos - 1] == null)
				{
					return null;
				}
				else
				{
					return gold[pos - 1];
				}
			}
		}
		else
		{
			if (pos > nSilver)
			{
				return null;
			}
			else
			{
				if (silver[pos - 1] == null)
				{
					return null;
				}
				else
				{
					return silver[pos - 1];
				}
			}
		}
	}


	@Override
	public Person refundSeat(int pos, Type type) {
		// TODO Auto-generated method stub
		
		Person aux = null;
		
		if (pos < 1)
		{
			return null;
		}
	
		if (type == Configuration.Type.GOLD)
		{
			if (pos > nGold)
			{
				return null;
			}
			else
			{
				if (gold[pos - 1] == null)
				{
					return null;
				}
				else
				{
					aux = gold[pos - 1].getHolder();
					gold[pos - 1] = null;
					
					return aux;
				}
			}
		}
		else
		{
			if (pos > nSilver)
			{
				return null;
			}
			else
			{
				if (silver[pos - 1] == null)
				{
					return null;
				}
				else
				{
					aux = silver[pos - 1].getHolder();
					silver[pos - 1] = null;
					
					return aux;
				}
			}
		}	
	}


	@Override
	public boolean sellSeat(int pos, Person p, Type type) {
		// TODO Auto-generated method stub
		
		if(pos < 1)
		{
			return false;
		}
		
		if (type == Configuration.Type.GOLD)
		{
			if (pos > nGold)
			{
				return false;
			}
			else
			{
				if (gold[pos - 1] != null)
				{
					return false;
				}
				else
				{
					Seat sitioNuevo = new Seat(this, pos, type, p);
					gold[pos - 1] = sitioNuevo;
					
					return true;
				}
			}
		}
		else
		{
			if (pos > nSilver)
			{
				return false;
			}
			else
			{
				if (silver[pos - 1] != null)
				{
					return false;
				}
				else
				{
					Seat sitioNuevo = new Seat(this, pos, type, p);
					silver[pos - 1] = sitioNuevo;
					
					return true;
				}
			}
		}
	}


	@Override
	public List<Integer> getAvailableGoldSeatsList() {
		// TODO Auto-generated method stub
		List<Integer> list;
		
		list = new ArrayList<>();
		
		for (int i = 0; i < nGold; i++)
		{
			if(gold[i] == null)
			{
				list.add(i + 1);
			}
		}
		
		return list;
	}


	@Override
	public List<Integer> getAvailableSilverSeatsList() {
		// TODO Auto-generated method stub
		
		List<Integer> list;
		
		list = new ArrayList<>();
				
		for (int i = 0; i < nSilver; i++)
		{
			if(silver[i] == null)
			{
				list.add(i + 1);
			}
		}
		
		return list;
	}


	@Override
	public Double getPrice(Seat seat) {
		// TODO Auto-generated method stub
		
		if(seat.getType() == Configuration.Type.GOLD)
		{
			return this.priceGold;
		}
		else
		{
			return this.priceSilver;
		}
	}


	@Override
	public Double getCollectionEvent() {
		// TODO Auto-generated method stub
		double collection = 0.0;
		
		collection = (getNumberOfSoldGoldSeats() * this.priceGold) + (getNumberOfSoldSilverSeats() * this.priceSilver);
		
		return collection;
	}


	@Override
	public int getPosPersonGold(Person p) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < nGold; i++)
		{
			if(gold[i] != null)
			{
				if (gold[i].getHolder() == p )
				{
					int pos = i + 1;
					return pos;
				}
			}
		}
		return -1;
	}


	@Override
	public int getPosPersonSilver(Person p) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < nSilver; i++)
		{
			if(silver[i] != null)
			{
				if (silver[i].getHolder() == p)
				{
					int pos = i + 1;
					return pos;
				}
			}
		}
		return -1;
	}


	@Override
	public boolean isGold(Person p) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < nGold; i++)
		{
			if (gold[i] != null)
			{
				if(gold[i].getHolder() == p )
				{
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public boolean isSilver(Person p) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nSilver; i++)
		{
			if(silver[i] != null)
			{
				if(silver[i].getHolder() == p)
				{
					return true;
				}
			}
		}
		return false;
	}	
}
