package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task("Description 1", "Title 1");
        Task task2 = new Task("Description 2", "Title 2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals("Description 1", tasks.get(0).getDesc());
        assertEquals("Title 1", tasks.get(0).getTitle());
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Description", "Title");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.addTask("Description", "Title");
        assertNotNull(createdTask);
        assertEquals("Description", createdTask.getDesc());
        assertEquals("Title", createdTask.getTitle());
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;

        doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
