package javaspring.technicaltest.core.service;

import javaspring.technicaltest.core.api.model.TransactionDto;

public interface TransactionService {
    void placeOrder(TransactionDto passed);
}
