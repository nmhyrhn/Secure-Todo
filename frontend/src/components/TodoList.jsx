import API from "../api/api";

export default function TodoList({ todos, setTodos }) {

  const toggleComplete = async (id, completed) => {
    try {
      const res = await API.put(`/todos/${id}`, { completed: !completed });
      setTodos(todos.map(t => t.id === id ? res.data : t));
    } catch (err) {
      alert("업데이트 실패");
    }
  };

  const handleDelete = async (id) => {
    try {
      await API.delete(`/todos/${id}`);
      setTodos(todos.filter(t => t.id !== id));
    } catch (err) {
      alert("삭제 실패");
    }
  };

  return (
    <ul>
      {todos.map(todo => (
        <li key={todo.id} className="flex justify-between items-center border p-2 mb-2">
          <div>
            <input type="checkbox" checked={todo.completed} onChange={() => toggleComplete(todo.id, todo.completed)} />
            <span className={todo.completed ? "line-through ml-2" : "ml-2"}>{todo.title}: {todo.content}</span>
          </div>
          <button onClick={() => handleDelete(todo.id)} className="bg-red-500 text-white p-1">삭제</button>
        </li>
      ))}
    </ul>
  );
}
