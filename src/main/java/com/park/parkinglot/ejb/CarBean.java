/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.entity.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mariu
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<CarDetails> getAllCars() {
        LOG.info("getAllCars");
        try {
            /*TypedQuery<Car> typedQuery = em.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> carsNoCast = typedQuery.getResultList();*/
            List<Car> cars = (List<Car>) em.createQuery("SELECT c FROM Car c").getResultList();
            return copyCarsToDetails(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    private List<CarDetails> copyCarsToDetails(List<Car> cars){
        List<CarDetails> detailsList=new ArrayList<>();
        for(Car car:cars){
            CarDetails carDetails=new CarDetails(car.getId(),car.getLicensePlate(),car.getParkingSpot(),car.getUser().getUsername());
            detailsList.add(carDetails);
        }
         return detailsList;   
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
