package com.bipinkarmacharya.journalApp.controller;

import com.bipinkarmacharya.journalApp.entity.JournalEntry;
import com.bipinkarmacharya.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> allJournal = journalEntryService.getJournalEntries();
        if (allJournal != null && !allJournal.isEmpty()) {
            return new ResponseEntity<>(allJournal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myJournalEntry) {
        try {
            journalEntryService.saveEntry(myJournalEntry);
            return new ResponseEntity<>(myJournalEntry, HttpStatus.CREATED);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable Long id, @RequestBody JournalEntry updatedJournalEntry) {
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(id).orElse(null);

        if (journalEntry != null) {
            journalEntry.setTitle(updatedJournalEntry.getTitle() != null && !updatedJournalEntry.getTitle().isEmpty() ? updatedJournalEntry.getTitle() : journalEntry.getTitle());
            journalEntry.setContent(updatedJournalEntry.getContent() != null && !updatedJournalEntry.getContent().isEmpty() ? updatedJournalEntry.getContent() : journalEntry.getContent());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
        journalEntryService.deleteJournalEntryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
