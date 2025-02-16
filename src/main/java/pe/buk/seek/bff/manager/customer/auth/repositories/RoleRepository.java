/*
 * @(#)RoleRepository.java
 *
 * Copyright (c) SEEK (Chile). All rights reserved.
 *
 * All rights to this product are owned by SEEK and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by SEEK.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package pe.buk.seek.bff.manager.customer.auth.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.buk.seek.bff.manager.customer.auth.entities.RoleEntity;

/**
 * RoleRepository.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Obtencion del rol por el nombre
     *
     * @param name {@link String}
     * @return {@link RoleEntity}
     */
    Optional<RoleEntity> findByName(final String name);

}