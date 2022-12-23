package ru.onbording.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.onbording.task1.model.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}
