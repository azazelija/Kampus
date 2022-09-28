package ru.kampus.rest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponsePage<T> {

    List<T> data;

    int totalPages;
    int page;
    int size;

    public ResponsePage(Page<T> page) {
          setData(page.getContent());
          setPage(page.getNumber());
          setSize(page.getSize());
          setTotalPages(page.getTotalPages());
    }
}
