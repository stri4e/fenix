package com.github.statistics.services;

import com.github.statistics.entity.UnRegClientView;

import java.util.List;

public interface IUnRegClientViewsService {

    UnRegClientView create(UnRegClientView view);

    List<UnRegClientView> read(String ip);

}
