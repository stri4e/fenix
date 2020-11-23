package com.github.statistics.services;

import com.github.statistics.entity.UnbookedClientView;

import java.util.List;

public interface IUnbookedClientViewsService {

    UnbookedClientView create(UnbookedClientView view);

    List<UnbookedClientView> read(String ip);

}
