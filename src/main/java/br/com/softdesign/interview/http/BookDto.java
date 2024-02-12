package br.com.softdesign.interview.http;

record BookDto(
        Long id,
        String name,
        String author,
        boolean rented) {
}
