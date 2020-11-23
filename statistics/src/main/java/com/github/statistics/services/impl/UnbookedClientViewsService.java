package com.github.statistics.services.impl;

import com.github.statistics.entity.UnbookedClientView;
import com.github.statistics.repositoty.UnbookedClientViewsRepo;
import com.github.statistics.services.IUnbookedClientViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UnbookedClientViewsService implements IUnbookedClientViewsService {

    private final UnbookedClientViewsRepo unbookedClientViewsRepo;

    @Override
    public UnbookedClientView create(UnbookedClientView view) {
        return this.unbookedClientViewsRepo.save(view);
    }

    @Override
    public List<UnbookedClientView> read(String ip) {
        return this.unbookedClientViewsRepo.findTop30ByIp(ip);
    }
}
