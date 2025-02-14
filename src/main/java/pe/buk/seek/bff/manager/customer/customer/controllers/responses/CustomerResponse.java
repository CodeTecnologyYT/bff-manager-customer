/*
 * @(#)CustomerResponse.java
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
package pe.buk.seek.bff.manager.customer.customer.controllers.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CustomerResponse.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    /** id. */
    private Long id;
    /** name. */
    private String name;
    /** lastname. */
    private String lastname;
    /** age. */
    private Integer age;
    /** dateBirth. */
    private LocalDate dateBirth;
    /** createdAt. */
    private LocalDateTime createdAt;

}
