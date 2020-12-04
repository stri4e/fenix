package com.github.statistics.services.impl;

import com.github.statistics.entity.UnRegClientView;
import com.github.statistics.repository.UnRegClientViewsRepo;
import com.github.statistics.services.IUnRegClientViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UnRegClientViewsService implements IUnRegClientViewsService {

    private final UnRegClientViewsRepo unRegClientViewsRepo;

    @Override
    public UnRegClientView create(UnRegClientView view) {
        return this.unRegClientViewsRepo.save(view);
    }

    @Override
    public List<UnRegClientView> read(String ip) {
        return this.unRegClientViewsRepo.findTop30ByIp(ip);
    }
}
