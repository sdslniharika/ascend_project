package com.globalLogic.swimmers.service;

import com.globalLogic.swimmers.entity.Swimmer;
import com.globalLogic.swimmers.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwimmerService implements ISwimmerService{
    @Autowired
    private SwimmerRepository swimmerRepository;
    @Override
    public Swimmer addSwimmer(Swimmer swimmer) {
        Swimmer swimmer1 = new Swimmer(swimmer.getName(), swimmer.getAge());
        swimmer1 = swimmerRepository.save(swimmer1);
        return swimmer1;
    }

    @Override
    public Swimmer findById(Integer id) {
        return swimmerRepository.findById(id).get();
    }

    @Override
    public List<Swimmer> findAll() {
        return swimmerRepository.findAll();
    }

    @Override
    public String deleteByID(Integer id) {
        swimmerRepository.deleteById(id);
        return "Swimmer Deleted";
    }
}
