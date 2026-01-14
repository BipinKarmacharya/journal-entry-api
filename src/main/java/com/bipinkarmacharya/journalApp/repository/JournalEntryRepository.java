package com.bipinkarmacharya.journalApp.repository;

import com.bipinkarmacharya.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}


// controller --> calls ---> service --> call --> Repository