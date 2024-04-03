package org.lshh.skeleton.sample.infrastructure.router;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.router.implement.RouterContext;
import org.lshh.skeleton.core.router.RouterRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RouterRepositoryImplement implements RouterRepository {
    private final RouterJpaRepository repository;

    @Override
    public Optional<RouterContext> findByPath(String path) {
        return repository.findByPath(path);
    }

    @Override
    public List<RouterContext> findAll() {
        return repository.findAll();
    }

    @Override
    public RouterContext create(RouterContext newOne) {
        return repository.save(newOne);
    }

    @Override
    public RouterContext update(RouterContext renewal) {
        return repository.save(renewal);
    }
}
