package ule.edi.model;

import java.util.Date;
import java.util.List;

import ule.edi.model.Configuration.Type;

public interface Event {
	
	
	public String getName();
	
	public Date getDate();
	
	public Double getPriceGold();
	
	public Double getPriceSilver();

	
	/**
	 * Calcula el número de butacas totales vendidas del evento.
	 * 
	 * @return
	 */
	public int getNumberOfSoldSeats();
	
	/**
	 * Calcula el número de butacas Gold vendidas del evento.
	 * 
	 * @return
	 */
	public int getNumberOfSoldGoldSeats();
	
	/**
	 * Calcula el número de butacas Silver vendidas del evento.
	 * 
	 * @return
	 */
	public int getNumberOfSoldSilverSeats();
	
	/**
	 * Número de butacas totales del evento (ocupadas y disponibles).
	 * 
	 * @return
	 */
	public int getNumberOfSeats();
	
	/**
	 * Número de butacas GOLD totales del evento (ocupadas y disponibles).
	 * 
	 * @return
	 */
	public int getNumberOfGoldSeats();
	
	/**
	 * Número de butacas SILVER totales del evento (ocupadas y disponibles).
	 * 
	 * @return
	 */
	public int getNumberOfSilverSeats();
	
	
	/**
	 * Calcula el número de butacas disponibles (no vendidas).
	 * 
	 * @return
	 */
	public int getNumberOfAvailableSeats();

	/**
	 * Devuelve la butaca en la posición dada y del tipo dado o null si no está ocupada
	 * 
	 * Las posiciones empiezan en '1'.
	 * 
	 * @param pos
	 * @return
	 */
	public Seat getSeat(int pos, Type type);
	

	/**
	 * Libera la butaca de la posición dada y del tipo dado. 
	 * 
     * Si la butaca de esa posición ya está libre, devuelve null.
	 * 
	 * Las posiciones empiezan en '1'.
	 * 
	 * 
	 * @param pos
	 * @return p :la persona que ocupaba la butaca o null si la butaca no estaba ocupada.
	 * 
	 */
	public Person refundSeat(int pos, Type type);

	/**
	 * 
	 * Si la butaca de esa posición ya está ocupada, no hace nada.
	 * 
	 * Las posiciones empiezan en '1'.
	 * 
	 * @param pos
	 * @param p : la persona que ocupará la butaca
	 * @return true indica si se pudo realizar la venta de la butaca, false en caso contrario
	 */
	public boolean sellSeat(int pos, Person p, Type type);
	

	/**
	 * Calcula el número de niños asistentes al evento.
	 * 
	 * 	[0, Configuration.CHILDREN_EXMAX_AGE)
	 * 
	 * CHILDREN_EXMAX_AGE no contabiliza como niño (menor que)
	 * 
	 * @return
	 */
	public int getNumberOfAttendingChildren();
	
	/**
	 * Calcula el número de adultos asistentes al evento.
	 * 
	 * 	[Configuration.CHILDREN_EXMAX_AGE, Configuration.ELDERLY_PERSON_INMIN_AGE)
	 * 
	 * ELDERLY_PERSON_INMIN_AGE no incluido como adulto (menor que)
	 * 
	 * @return
	 */
	public int getNumberOfAttendingAdults();

	/**
	 * Calcula el número de ancianos asistentes al evento.
	 * 
	 * 	[Configuration.ELDERLY_PERSON_INMIN_AGE, Integer.MAX_VALUE)
	 * 
	 * ELDERLY_PERSON_INMIN_AGE incluido como anciano
	 *  
	 * @return
	 */
	public int getNumberOfAttendingElderlyPeople();

	
	/**
	 * Calcula la lista de números de butacas gold disponibles
	 * Tener en cuenta que las posiciones empiezan en 1
	 * 
	 * @return lista de posiciones disponibles 
	 */
    public List<Integer> getAvailableGoldSeatsList();
    
	
	/**
	 * Calcula la lista de números de butacas silver disponibles
	 * Tener en cuenta que las posiciones empiezan en 1
	 * 
	 * 	 @return lista de posiciones disponibles 
	 */
    public List<Integer> getAvailableSilverSeatsList();

    /**
	 * Calcula el precio de la butaca en función del tipo de butaca y del precio de ese tipo de butaca para el evento al que pertenece
	 * Tener en cuenta que las posiciones empiezan en 1
	 * 
	 * 	 @return lista de posiciones disponibles 
	 */
	public Double getPrice(Seat seat);

   	
	/**
	 * Calcula el importe total recaudado por las butacas ocupadas
	 * 
	 * 	 @return importe total recaudado
	 */
	public Double getCollectionEvent();
	
	/**
	 * 
	 * @param p persona a buscar en la parte GOLD
	 * @return la butaca que ocupa la persona o -1 si no está
	 */
	public int getPosPersonGold(Person p);
	
	/**
	 * 
	 * @param p persona a buscar en la parte SILVER
	 * @return la butaca que ocupa la persona o -1 si no está
	 */
	public int getPosPersonSilver(Person p);
	
	/**
	 * 
	 * @param p persona a buscar en la parte GOLD
	 * @return true si la persona ocupa una butaca, false en caso contrario
	 */
	public boolean isGold(Person p);
	
	/**
	 * 
	 * @param p persona a buscar en la parte SILVER
	 * @return true si la persona ocupa una butaca, false en caso contrario
	 */
	public boolean isSilver(Person p);
}
