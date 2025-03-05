package com.nubank.capitalgain.application.domain.calculator;

/**
 * Interface to calculate a value based on a given input.
 *
 * @param <T> the input typeOperation
 * @param <R> the output typeOperation
 */
public interface Calculator<T, R> {

    /**
     * Compute a value based on the given input.
     *
     * @param t the input
     * @return the computed value
     */
    R computedValue(T t);
}
