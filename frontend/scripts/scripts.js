async function test(){
  try {
    const res = await axios.get("http://localhost:8080/users")
    console.log(res.data);
  } catch (err) {
    console.error(err.response.data);
  }
}

async function loadTasks(){
  try {
    const res = await axios.get("http://localhost:8080/tasks/2")
    const tasks = res.data
    console.log(tasks);

    const tableTasks = document.getElementById("tasks")

    tasks.forEach(task => {
      const row = document.createElement("div")
      row.setAttribute("class", "row")

      row.innerHTML = `
        <div class="task-item">${task.id}</div>
        <div class="task-item">${task.title}</div>
        <div class="task-item">${task.description}</div>
        <div class="task-item">${task.createdAt}</div>
        <div class="task-item">${task.deadline}</div>
      `
      tableTasks.appendChild(row)
    })
  } catch (err) {
    console.error(err)
  }
}

const button = document.getElementById("get-tasks")

button.addEventListener("click", loadTasks)

