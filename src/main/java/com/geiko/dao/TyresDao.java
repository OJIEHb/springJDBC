package com.geiko.dao;

import com.geiko.model.Tyres;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TyresDao {
    public Tyres getTyresByWheelId(int id);
    public void addTyres(Tyres tyres);
}
