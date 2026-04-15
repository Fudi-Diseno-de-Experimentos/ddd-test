package com.examples.dddtest.shared.domain.results;

// Sealed result type for domain service operations.
// Forces callers to handle both success and failure exhaustively

public sealed interface ServiceResult<T> {

    record Success<T>(T value) implements ServiceResult<T> {}

    record Failure<T>(String reason) implements ServiceResult<T> {}
}