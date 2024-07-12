package org.lshh.skeleton.library.transaction.keyword;

public interface TransactionCommand {
    enum ThisTransactionCommand {
        GET_INPUT,
        SET_OUTPUT,
        GET_VAR,
        SET_VAR,
        FIND_QUERY,
        FIND_TRANSACTION,

    }

    enum QueryCommand {
        SET_INPUT,
        GET_OUTPUT,
        QUERY
    }

    enum ChildTransactionCommand{
        GET_OUTPUT,
        SET_INPUT,
        RUN_TRANSACTION
    }

    enum DataSetCommand {
        SELECT,
        WHERE,
        INNER_JOIN,
        LEFT_JOIN,
    }

    enum JoinSetCommand {
        ON,
        ON_SAME,
        SELECT_LEFT,
        SELECT_RIGHT,
        TO_DATASET
    }
}
