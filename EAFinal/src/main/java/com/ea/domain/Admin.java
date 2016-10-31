package com.ea.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ossama on 9/25/2016.
 */

@Entity
@DiscriminatorValue(value = "ROLE_ADMIN")
public class Admin extends User {

    public Admin()
    {

    }

}
