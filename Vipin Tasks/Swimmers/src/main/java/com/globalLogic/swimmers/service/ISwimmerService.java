package com.globalLogic.swimmers.service;

import com.globalLogic.swimmers.entity.Swimmer;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ISwimmerService {
    Swimmer addSwimmer(Swimmer swimmer);
    Swimmer findById(Integer id);
    List<Swimmer> findAll();
    String deleteByID(Integer id);
}
