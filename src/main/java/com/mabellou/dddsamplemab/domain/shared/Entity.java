package com.mabellou.dddsamplemab.domain.shared;

public interface Entity<T> {

    boolean sameIdentityAs(T other);

}
