import React, { useState } from 'react';
import './TodoList.css';

interface Task {
  task: string;
  deadline: string;
}

function TodoList() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [task, setTask] = useState<string>('');
  const [deadline, setDeadline] = useState<string>('');
  const [assignee, setAssignee] = useState<string>('');

  const addTask = () => {
    if (task && deadline) {
      setTasks([...tasks, { task, deadline }]);
      setTask('');
      setDeadline('');
      setAssignee('');
    }
  };

  const deleteTask = (index: number) => {
    const newTasks = tasks.filter((_, i) => i !== index);
    setTasks(newTasks);
  };

  return (
    <div className="todo-list">
      <h1>TODO List</h1>
      <div className="input-container">
        <input
          type="text"
          placeholder="Task"
          value={task}
          onChange={(e) => setTask(e.target.value)}
        />
        <input
          type="date"
          value={deadline}
          onChange={(e) => setDeadline(e.target.value)}
        />
        <input
          type="text"
          placeholder="Assignee"
          value={assignee}
          onChange={(e) => setAssignee(e.target.value)}
        />
        <button onClick={addTask}>Add Task</button>
      </div>
      <ul>
        {tasks.map((t, index) => (
          <li key={index}>
            <span>{t.task} - {t.deadline}</span>
            <button onClick={() => deleteTask(index)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList; 