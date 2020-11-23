package com.github.statistics.repositoty;

import com.github.statistics.entity.UnRegClientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnRegClientViewsRepo extends JpaRepository<UnRegClientView, Long> {

    List<UnRegClientView> findTop30ByIp(String ip);

}
