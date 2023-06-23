package org.globaltrainings.dao;

import org.globaltrainings.datastore.BusDB;
import org.globaltrainings.entity.Bus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusDao implements IBusDao{
    @Override
    public Bus addBus(Bus bus) {
        BusDB.map.put(bus.getId(), bus);
        return bus;
    }

    @Override
    public List<Bus> findAllBuses() {
        List<Bus> busList = BusDB.map.values().stream().collect(Collectors.toList());
        if(busList.isEmpty()){
            throw new RuntimeException("No Busses available at the moment");
        }
        else{
            return busList;
        }
    }

    @Override
    public Bus findBusById(int id) {
        if (BusDB.map.containsKey(id)){
            return BusDB.map.get(id);
        }
        else{
            throw new RuntimeException("No Bus Found wiith taht ID");
        }
    }
}
