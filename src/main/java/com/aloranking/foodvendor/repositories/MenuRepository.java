package com.aloranking.foodvendor.repositories;

import com.aloranking.foodvendor.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MenuRepository extends JpaRepository<Menu, Long> {

public Iterable<Menu> findByIdIn(Collection<Long> menuIds);
}
