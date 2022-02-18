package org.efire.net.vendomachine_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.efire.net.vendomachine_api.domain.VendingMachine;


public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long> {
    // add custom queries here
}
