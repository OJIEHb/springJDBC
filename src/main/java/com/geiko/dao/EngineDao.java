package com.geiko.dao;

import com.geiko.model.Engine;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EngineDao {
    public Engine getEngineByCarId(int id);
    public void addEngine(Engine engine);

}
