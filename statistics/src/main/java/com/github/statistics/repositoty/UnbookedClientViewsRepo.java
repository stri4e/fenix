package com.github.statistics.repositoty;

import com.github.statistics.entity.UnbookedClientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnbookedClientViewsRepo extends JpaRepository<UnbookedClientView, Long> {

    List<UnbookedClientView> findTop30ByIp(String ip);

}
