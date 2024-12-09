package com.gr1t.taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    public void testEnumValues() {
        Priority[] priorities = Priority.values();

        assertEquals(3, priorities.length, "Priority enum should have 3 values.");
        assertEquals(Priority.HIGH, priorities[0], "First value should be HIGH.");
        assertEquals(Priority.MEDIUM, priorities[1], "Second value should be MEDIUM.");
        assertEquals(Priority.LOW, priorities[2], "Third value should be LOW.");
    }

    @Test
    public void testValueOf() {
        assertEquals(Priority.HIGH, Priority.valueOf("HIGH"));
        assertEquals(Priority.MEDIUM, Priority.valueOf("MEDIUM"));
        assertEquals(Priority.LOW, Priority.valueOf("LOW"));
    }
}