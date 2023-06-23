package org.globaltrainings.dao;

import org.globaltrainings.entity.Bus;

import java.util.List;

public interface IBusDao {
    Bus addBus(Bus bus);
    List<Bus> findAllBuses();
    Bus findBusById(int id);
}
