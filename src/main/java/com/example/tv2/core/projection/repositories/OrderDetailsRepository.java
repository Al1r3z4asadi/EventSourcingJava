package com.example.tv2.core.projection.repositories;

import com.example.tv2.core.projection.model.OrderDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsView, UUID> {

    @Query("SELECT d FROM OrderDetailsView d WHERE d.id = ?1")
    Optional<OrderDetailsView> findById(UUID id);
}

