package org.lshh.skeleton.library.variable.unit;

import org.lshh.skeleton.library.transaction.Transaction;
import org.lshh.skeleton.library.variable.Variable;
import org.lshh.skeleton.library.resource.query.Query;

public abstract class UnitVariable implements Unit, Variable {
    public static <T> UnitVariable of(T unit) {
        return switch (unit) {
            case Query query -> QueryUnit.of(query);
            case Transaction transaction -> TransactionUnit.of(transaction);
            case null, default -> throw new IllegalArgumentException("Unknown unit type");
        };
    }
}
