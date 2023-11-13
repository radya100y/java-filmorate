package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.dao.impl.MpaDao;

@Service
public class MpaService extends BaseService<Mpa, MpaDao> {

    protected MpaService(MpaDao storage) {
        super(storage);
    }
}
